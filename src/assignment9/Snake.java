package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		segments = new LinkedList<>();
		BodySegment head = new BodySegment(0,0,SEGMENT_SIZE);
		segments.add(head);
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move(Food food) {
		BodySegment newSeg = new BodySegment(segments.getFirst().getX() + deltaX, segments.getFirst().getY() + deltaY, SEGMENT_SIZE);
		segments.addFirst(newSeg);
		if (this.eatFood(food) == false){
			segments.removeLast();
		}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment s : segments){
			s.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		if (Math.sqrt((Math.pow(segments.getFirst().getX() - f.getX(), 2) + Math.pow(segments.getFirst().getY() - f.getY(), 2))) <= Food.FOOD_SIZE + SEGMENT_SIZE){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		double headX = segments.getFirst().getX();
		double headY = segments.getFirst().getY();
		if (headX > 1 || headX < 0 || headY > 1 || headY < 0){
			return false;
		}
		return true;
	}
}
