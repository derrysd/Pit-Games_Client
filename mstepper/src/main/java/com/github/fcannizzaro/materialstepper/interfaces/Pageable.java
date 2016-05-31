package com.github.fcannizzaro.materialstepper.interfaces;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import java.util.List;

/**
 * Created by Francesco Cannizzaro on 08/05/2016.
 */
public interface Pageable {

    void add(AbstractStep fragment);

    void set(List<AbstractStep> fragments);

}
