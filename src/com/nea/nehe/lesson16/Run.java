/*
 * Copyright (C) 2010 0xlab - http://0xlab.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authored by Julian Chu <walkingice@0xlab.org>
 */

package com.nea.nehe.lesson16;

import org.zeroxlab.benchmark.Tester;

import android.os.Bundle;

/**
 * The initial Android Activity, setting and initiating
 * the OpenGL ES Renderer Class @see Lesson16.java
 *
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class Run extends Tester {

    public final static String FullName = "com.nea.nehe.lesson16.Run";

    /** Our own OpenGL View overridden */
    private Lesson16 lesson16;

    @Override
    public String getTag() {
        return "Nehe16";
    }

    @Override
    public int sleepBeforeStart() {
        return 1200; //1.2 second
    }

    @Override
    public int sleepBetweenRound() {
        return 0;
    }

    @Override
    protected void oneRound() {
//        lesson16.requestRender();
    }

    /**
     * Initiate our @see Lesson16.java,
     * which is GLSurfaceView and Renderer
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initiate our Lesson with this Activity Context handed over
        lesson16 = new Lesson16(this);
        lesson16.setSpeedAndTester(1, 1, this);
        //Set the lesson as View to the Activity
        setContentView(lesson16);
        startTester();
    }

    /**
     * Remember to resume our Lesson
     */
    @Override
    protected void onResume() {
        super.onResume();
        lesson16.onResume();
    }

    /**
     * Also pause our Lesson
     */
    @Override
    protected void onPause() {
        super.onPause();
        lesson16.onPause();
    }

}
