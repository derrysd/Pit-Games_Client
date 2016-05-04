/*
   Copyright 2013 Harri Smatt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

//
// Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>
// For additional information please go and see:
// http://incubator.quasimondo.com/processing/fast_blur_deluxe.php
//

#pragma version(1)
#pragma rs java_package_name(com.github.fcannizzaro.materialstepper)
#pragma rs_fp_relaxed

//#include <rs_types.rsh>

//
// For holding current bitmap size
//
typedef struct SizeStruct {
    int width;
    int height;
} SizeStruct_t;
SizeStruct_t sizeStruct;

//
// For holding current stack blur related variables
//
typedef struct RadiusStruct {
    int radius;
    int div;
    int divsum;
} RadiusStruct_t;
RadiusStruct_t radiusStruct;

//
// Variables for dynamic allocations
//
rs_allocation bitmap;
rs_allocation bitmapTmp;
uint8_t* dv;

//
// For initializing divisor allocation
//
int initializeDv_divsum;
void initializeDv(uint8_t* out, const void* userdata, uint32_t x) {
    *out = x / initializeDv_divsum;
}

//
// Handles horizontal stack blur step
//
void blurHorizontal(const uchar4* unused, const void* userData, uint32_t x, uint32_t y) {
    uint3 sum = 0, insum = 0, outsum = 0;
    uint8_t r1 = radiusStruct.radius + 1;
    int wmax = sizeStruct.width - 1;

    uint16_t stackstart;
    uint16_t stackpointer = radiusStruct.radius;
    uint3 stack[radiusStruct.radius + radiusStruct.radius + 1];

    for (int i = -radiusStruct.radius; i <= radiusStruct.radius; i++) {
        uchar4 p = rsGetElementAt_uchar4(bitmap, min(wmax, max(i, 0)), y);

        uint3 *sir = &stack[i + radiusStruct.radius];
        (*sir).r = p.r;
        (*sir).g = p.g;
        (*sir).b = p.b;

        sum += (*sir) * (r1 - abs(i));
        if (i > 0) {
            insum += *sir;
        } else {
            outsum += *sir;
        }
    }

    for (int x = 0; x < sizeStruct.width; ++x) {
        uchar4 outValue = { dv[sum.r], dv[sum.g], dv[sum.b], 0xFF };
        rsSetElementAt_uchar4(bitmapTmp, outValue, x, y);

        sum -= outsum;

        stackstart = stackpointer - radiusStruct.radius + radiusStruct.div;
        uint3* sir = &stack[stackstart % radiusStruct.div];
        outsum -= *sir;

        uchar4 p = rsGetElementAt_uchar4(bitmap, min(x + r1, wmax), y);
        (*sir).r = p.r;
        (*sir).g = p.g;
        (*sir).b = p.b;

        insum += *sir;
        sum += insum;

        stackpointer = (stackpointer + 1) % radiusStruct.div;
        sir = &stack[(stackpointer) % radiusStruct.div];

        outsum += *sir;
        insum -= *sir;
    }
}

//
// Handles vertical stack blur step
//
void blurVertical(const uchar4* unused, const void* userData, uint32_t x, uint32_t y) {
    uint3 sum = 0, insum = 0, outsum = 0;

    uint8_t r1 = radiusStruct.radius + 1;
    int hmax = sizeStruct.height - 1;

    uint16_t stackstart;
    uint16_t stackpointer = radiusStruct.radius;
    uint3 stack[radiusStruct.radius + radiusStruct.radius + 1];

    for (int i = -radiusStruct.radius; i <= radiusStruct.radius; i++) {
        uint3* sir = &stack[i + radiusStruct.radius];
        uchar4 inValue = rsGetElementAt_uchar4(bitmapTmp, x, min(hmax, max(0, i)));
        (*sir).r = inValue.r;
        (*sir).g = inValue.g;
        (*sir).b = inValue.b;

        sum += (*sir) * (r1 - abs(i));

        if (i > 0) {
            insum += *sir;
        } else {
            outsum += *sir;
        }
    }

    for (int y = 0; y < sizeStruct.height; y++) {
        uchar4 outValue = { dv[sum.r], dv[sum.g], dv[sum.b], 0xFF };
        rsSetElementAt_uchar4(bitmap, outValue, x, y);

        sum -= outsum;

        stackstart = stackpointer - radiusStruct.radius + radiusStruct.div;
        uint3* sir = &stack[stackstart % radiusStruct.div];
        outsum -= *sir;

        uchar4 inValue = rsGetElementAt_uchar4(bitmapTmp, x, min(y + r1, hmax));
        (*sir).r = inValue.r;
        (*sir).g = inValue.g;
        (*sir).b = inValue.b;
        insum += *sir;
        sum += insum;

        stackpointer = (stackpointer + 1) % radiusStruct.div;
        outsum += stack[stackpointer];
        insum -= stack[stackpointer];
    }
}