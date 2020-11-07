/*
 * Copyright 2020 Gabriel Keller
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * A copy of this license can be found at
 * https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.
 */

package com.okgabe.mastr2.util;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public interface ActionableFuture<T> {

    IActionableFuture<T> onError(@Nonnull Consumer<? super Throwable> callback);
    IActionableFuture<T> onSuccess(@Nonnull Consumer<T> success);
}
