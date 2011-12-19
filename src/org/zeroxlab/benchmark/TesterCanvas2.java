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
 */

package org.zeroxlab.benchmark;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;

public class TesterCanvas2 extends Tester implements SurfaceHolder.Callback2 {
    public final String TAG = "TesterCanvas2";
    public final static String PACKAGE = "org.zeroxlab.benchmark";
    private Random mRandom;
    private Paint mPaint;
    private SurfaceHolder mSurfaceHolder;
    
    public String getTag() {
        return TAG;
    }

    public static String getPackage() {
        return PACKAGE;
    }

    public static String getFullClassName() {
        return getPackage() + ".TesterCanvas2";
    }

    public int sleepBetweenRound() {
        return 0;
    }
    public int sleepBeforeStart() {
        return 1000;
    }

    public void oneRound() {
    	if (mSurfaceHolder == null)
    		return;
    	
		Canvas canvas = mSurfaceHolder.lockCanvas();
    	Log.d("G", "Case " + Case.getSource(getIntent()) 
    			+ ", canvas " + canvas.toString() + " HW Acc : " + canvas.isHardwareAccelerated()
    			+ ", thread: " + Thread.currentThread().toString());
    	
        int r = (0x00151515| mRandom.nextInt() ) | Color.BLACK;
        if (Benchmark.sUseTexture || Benchmark.sUseGradient)
        	canvas.drawPaint(mPaint);
        else
        	canvas.drawRGB(r, r, r);	
        
        mSurfaceHolder.unlockCanvasAndPost(canvas);
        decreaseCounter();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mRandom = new Random();
        getWindow().takeSurface(this);        
        mPaint = new Paint();
        if (Benchmark.sUseTexture)
        	mPaint.setShader(Benchmark.createBitmapShader());
        else
        	mPaint.setShader(Benchmark.createGradient());
        
        startTester();
    }

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mSurfaceHolder = holder;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mSurfaceHolder = null;
	}

	@Override
	public void surfaceRedrawNeeded(SurfaceHolder holder) {
	}
}
