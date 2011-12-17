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

import org.zeroxlab.benchmark.Case;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class DrawArcView extends SurfaceView {

    private SurfaceHolder mSurfaceHolder;
    private int angle = 0;
    private int step = 5;
    //private boolean center = false;

    protected void doDraw() {
        Canvas canvas = mSurfaceHolder.lockCanvas();
        drawArc(canvas);
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    private void drawArc(Canvas canvas) {
    	Log.d("G", "Case " + Case.getSource(((Activity)getContext()).getIntent()) 
    			+ ", canvas " + canvas.toString() + " HW Acc : " + canvas.isHardwareAccelerated()
    			+ ", layer : " + getLayerType() + "(0:None, 1:SW, 2:HW)");
    	
        if (angle > 360) angle = 0;

        int color = (0x00252525 | new Random().nextInt() ) | Color.BLACK;
        Paint p = new Paint();
        p.setAntiAlias(false);
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);

        canvas.drawArc(new RectF(0,0, getWidth(), getHeight()), 0, angle, true, p);

        for(int j=0; j<3; j++) for(int x=0; x<4; x++) for(int y=0; y<4; y++) {
            color = (0x88252525 | new Random().nextInt() );
            p = new Paint();
            p.setAntiAlias(false);
            p.setStyle(Paint.Style.FILL);
            p.setColor(color);

            if(x%2==0)
                canvas.drawArc(new RectF( x*getWidth()/4, y*getHeight()/4, (1+x)*getWidth()/4, (1+y)*getHeight()/4), 0, angle, (x+y)%2 == 0, p);
            else
                canvas.drawArc(new RectF( x*getWidth()/4, y*getHeight()/4, (1+x)*getWidth()/4, (1+y)*getHeight()/4), 0, -angle, (x+y)%2 == 0, p);
        }
        angle += step;
    }

    public DrawArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
    }
}

