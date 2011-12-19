/*
 * Copyright (C) 2010 0xlab - http://0xlab.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.zeroxlab.graphics;

import java.util.Random;

import org.zeroxlab.benchmark.Benchmark;
import org.zeroxlab.benchmark.Case;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class DrawCircle2View extends SurfaceView {

    private SurfaceHolder mSurfaceHolder;

    protected void doDraw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        drawCircle(canvas);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void drawCircle(Canvas canvas) {    	
    	Log.d("G", "Case " + Case.getSource(((Activity)getContext()).getIntent()) 
    			+ ", canvas " + canvas.toString() + " HW Acc : " + canvas.isHardwareAccelerated()
    			+ ", layer : " + getLayerType() + "(0:None, 1:SW, 2:HW)");
    	
        Random mRandom = new Random();

        int height = getHeight();
        int width  = getWidth();

        int cx = (int)((mRandom.nextInt() % (width*0.8) ) + (width*0.1));
        int cy = (int)((mRandom.nextInt() % (height*0.8) ) + (height*0.1));
        int  r = (int)((mRandom.nextInt() % (width*0.3) ) + (width*0.2));

        int color;
        Paint p;
        for(int i=6; i>=0; i--) {
            color = (0x33252525 | mRandom.nextInt()); 
            p = new Paint();
            p.setAntiAlias(true);
            p.setStyle(Paint.Style.FILL);
            if (Benchmark.sUseTexture)
            	p.setShader(Benchmark.createBitmapShader());
            else if (Benchmark.sUseGradient)
            	p.setShader(Benchmark.createGradient());
            else
            	p.setColor(color);
            canvas.drawCircle(cx, cy, (int)(r*(1 + i/10.0)), p);
        }
    }

    public DrawCircle2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
    }
}

