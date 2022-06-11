package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class Board {

    private final List<List<Tile>> tiles;

    private final int size;

    public Board(int size) {
        this.size = size;
        this.tiles = new ArrayList<>();

        for(int i=0; i<size; i++) {
            List<Tile> row = new ArrayList<>();
            for(int j=0; j<size; j++) {
                row.add(new Tile(0));
            }
            this.tiles.add(row);
        }
    }

    public void slide(SlideDirection slideDirection) {
        switch (slideDirection) {
            case LEFT:
                slideLeft();
                break;
            case RIGHT:
                slideRight();
                break;
            case UP:
                slideUp();
                break;
            case DOWN:
                slideDown();
                break;
        }
    }

    private void slideLeft() {
        for(int i=0; i<size; i++) {
            List<Tile> row = this.tiles.get(i);
            List<Tile> newRow = new ArrayList<>();
            int prev=0;
            for(int j=0; j<size; j++) {
                int cur = row.get(j).getValue();
                if(cur == 0) {
                    continue;
                }
                if(prev == 0) {
                    prev = cur;
                    continue;
                }
                if(prev == cur) {
                    newRow.add(new Tile(prev + cur));
                    prev = 0;
                }
                else {
                    newRow.add(new Tile(prev));
                    prev = cur;
                }
            }
            newRow.add(new Tile(prev));
            while(newRow.size() != row.size()) {
                newRow.add(new Tile(0));
            }
            this.tiles.set(i, newRow);
        }
    }

    private void slideRight() {
        for(int i=0; i<size; i++) {
            List<Tile> row = this.tiles.get(i);
            List<Tile> newRow = new ArrayList<>();
            int prev=0;
            for(int j=size-1; j>=0; j--) {
                int cur = row.get(j).getValue();
                if(cur == 0) {
                    continue;
                }
                if(prev == 0) {
                    prev = cur;
                    continue;
                }
                if(prev == cur) {
                    newRow.add(new Tile(prev + cur));
                    prev = 0;
                }
                else {
                    newRow.add(new Tile(prev));
                    prev = cur;
                }
            }
            newRow.add(new Tile(prev));
            while(newRow.size() != row.size()) {
                newRow.add(new Tile(0));
            }
            Collections.reverse(newRow);
            this.tiles.set(i, newRow);
        }
    }

    private void slideUp() {
        transpose();
        slideLeft();
        transpose();
    }

    private void slideDown() {
        transpose();
        slideRight();
        transpose();
    }

    private void transpose() {
        for(int i=0; i<size; i++) {
            for(int j=i+1; j<size; j++) {
                Tile temp1 = tiles.get(i).get(j);
                Tile temp2 = tiles.get(j).get(i);
                tiles.get(i).set(j, temp2);
                tiles.get(j).set(i, temp1);
            }
        }
    }

    public boolean addTile(Tile tile) {
        if(boardFilled()) {
            return false;
        }
        int i = (int) (Math.random() * size);
        int j = (int) (Math.random() * size);
        while(!isTileEmpty(i, j)) {
            i = (int) (Math.random() * size);
            j = (int) (Math.random() * size);
        }
        this.tiles.get(i).set(j, tile);
        return true;
    }

    private boolean isTileEmpty(int i, int j) {
        return this.tiles.get(i).get(j).getValue() == 0;
    }

    public boolean boardFilled() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(isTileEmpty(i, j)) return false;
            }
        }
        return true;
    }

    public boolean slidePossible() {
        for(int i=1; i<size-1; i++) {
            for(int j=1; j<size-1; j++) {
                Tile cur = tiles.get(i).get(j);
                Tile left = tiles.get(i).get(j-1);
                Tile right = tiles.get(i).get(j+1);
                Tile top = tiles.get(i-1).get(j);
                Tile down = tiles.get(i+1).get(j);
                if(cur.equals(left) || cur.equals(right) || cur.equals(top) || cur.equals(down)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tile getMaxTile() {
        Tile maxTile = new Tile(0);
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                Tile cur = tiles.get(i).get(j);
                if(cur.getValue() > maxTile.getValue()) {
                    maxTile = cur;
                }
            }
        }
        return maxTile;
    }

    public void print() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                int val = this.tiles.get(i).get(j).getValue();
                System.out.print((val > 0 ? val : "_") + " ");
            }
            System.out.println();
        }
    }
}
