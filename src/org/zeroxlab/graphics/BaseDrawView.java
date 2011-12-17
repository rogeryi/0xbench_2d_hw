package org.zeroxlab.graphics;

import org.zeroxlab.benchmark.Case;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public abstract class BaseDrawView extends View {

    private int count = 0;
    private long total1 = 0;
    private long total2 = 0;
    private volatile long now = 0;
	private final Object drawMutex = new Object();
	
    public BaseDrawView(Context context) {
		super(context);
		
        if (Case.isSoftwareLayer(((Activity)context).getIntent()))
        	setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
    
    public BaseDrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
        if (Case.isSoftwareLayer(((Activity)context).getIntent()))
        	setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}
    
    @Override
	protected void onDraw(Canvas c) {
   	
    	if (now == 0)
    		return;
    	
    	long eclipsed1 = System.currentTimeMillis() - now;
    	total1 += eclipsed1;
    	count++;
    	
    	//call subclass draw method
    	drawScene(c);
        
    	long eclipsed2 = System.currentTimeMillis() - now;
    	total2 += eclipsed2;
    	
    	if (count == 1 || count == 10 || count == 100) {
	    	Log.d("G", "Case " + Case.getSource(((Activity)getContext()).getIntent()) 
	    			+ ", canvas " + c.toString() + " HW Acc : " + c.isHardwareAccelerated()
	    			+ ", layer : " + getLayerType() + "(0:None, 1:SW, 2:HW)"
	    			+ ", count : " + count + ", eclipsed : " + eclipsed1 + "/" + eclipsed2
	    			+ ", total : (" + total1 + "/" + count + "/" + total1/count + ")/("
	    			+ total2 + "/" + count + "/" + total2/count + ")");
    	}
    	
        //let go for next frame
    	synchronized (drawMutex) {
			drawMutex.notify();
		}
	}

    public void doDraw() {

    	//ask repaint
    	now = System.currentTimeMillis();
    	postInvalidate();
    	
    	//wait draw finished
    	synchronized (drawMutex) {
        	try {
				drawMutex.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
    }
    
    public abstract void drawScene(Canvas canvas);
}
