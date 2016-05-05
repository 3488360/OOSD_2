package model;

import java.util.Observable;
import java.util.Timer;

import java.util.TimerTask;

//Help with this from http://stackoverflow.com/questions/14393423/how-to-make-a-countdown-timer-in-java
public class GameTimer extends Observable {
	private Timer time;
	private int interval;
	private int startInterval;
	private TimerTask task;
	
	public GameTimer (int interval) {
		this.interval = interval;
		this.startInterval = interval;
	}
	
	public GameTimer (int interval, int start) {
		this.interval = start;
		this.startInterval = interval;
	}

	public void start() {
		time = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				interval();
				setChanged();
				notifyObservers(interval);
			}
		};
		time.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	private int interval() {
		if (interval == 1) {
			time.cancel();
		}
		return --interval;
	}

	public void stop() {
		time.cancel();
		time.purge();
		interval = startInterval;
	}
}
