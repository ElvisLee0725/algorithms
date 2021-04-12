// trajectories!

// On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:

// "G": go straight 1 unit;
// "L": turn 90 degrees to the left;
// "R": turn 90 degrees to the right.
// The robot performs the instructions given in order, and repeats them forever.

// Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

// Calculate the result of robot running instructions once. Get the final result and position it moved
// Robot stays in cycle iff it moves 0 or it has changed direction after running instruction
// Directions: North, East, South, West: [[-1, 0], [0, 1], [1, 0], [0, -1]]
// Use x and y to represent coordinates, curDir is current direction and in range 0 to 3
class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().isRobotBounded("GGRGGRGGRGGR"));
    }
    public boolean isRobotBounded(String instructions) {
        int [][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int x = 0, y = 0;
        int curDir = 0;

        for(char ins : instructions.toCharArray()) {
            if(ins == 'L') {
                curDir = (curDir + 3) % 4;
            }
            else if(ins == 'R') {
                curDir = (curDir + 1) % 4;
            }
            else {
                x += dir[curDir][0];
                y += dir[curDir][1];
            }
        }
        return (x == 0 && y == 0) || (curDir != 0);
    }
}
