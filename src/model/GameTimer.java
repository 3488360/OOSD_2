package model;

import java.util.Timer;

import java.util.TimerTask;

import controller.GameController;

//Help with this from http://stackoverflow.com/questions/14393423/how-to-make-a-countdown-timer-in-java
public class GameTimer {
	private Timer time;
	private int interval;
	private int startInterval;
	private TimerTask task;
	
	public GameTimer (GameController userInterface, int interval) {
		this.interval = interval;
		this.startInterval = interval;
	}

	public void start() {
		time = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				interval();
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
	
	public int checkTime() {
		return interval;
	}
}
