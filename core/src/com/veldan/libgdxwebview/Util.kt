package com.veldan.libgdxwebview

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable

fun SpriteBatch.begend(block: SpriteBatch.() -> Unit) {
    begin()
    block()
    end()
}

fun disposeAll(vararg disposable: Disposable){
    disposable.onEach { it.dispose() }
}