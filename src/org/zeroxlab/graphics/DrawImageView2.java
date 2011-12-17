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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;


class DrawImageView2 extends BaseDrawView {

    private final int COL = 5;
    private final int ROW = 9;
    private float position[] = new float[ROW];
    private boolean direction[] = new boolean[ROW];
    private Bitmap mBitmap;
    private Paint bgPaint;

    protected void setImage(Bitmap bmp) {
        mBitmap = bmp;
    }

    public void drawScene(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        canvas.drawRect(0,0,w,h,bgPaint);

        for(int x=0; x<ROW; x++) {
            int speed = (x+1) * 2;
            
            for(int j=0; j<COL; j++) {
            	RectF r = new RectF((w/(float)COL)*j, position[x], (w/(float)COL)*(j+1), position[x]+(w/(float)COL));
            	Rect r2 = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
                canvas.drawBitmap(mBitmap, r2, r, null);
            }
            if(direction[x]) {
                position[x] += speed;
                if (position[x] + (w/(float)COL) >= getHeight())
                    direction[x] = !direction[x];
            } else {
                position[x] -= speed;
                if (position[x] <= 0)
                    direction[x] = !direction[x];
            }

        }
    }

    public DrawImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        bgPaint = new Paint();
        bgPaint.setColor(Color.BLACK);
        bgPaint.setStyle(Paint.Style.FILL);

        for(int i=0; i<ROW; i++) {
            position[i] = 0;
            direction[i] = true;
        }
    }
}