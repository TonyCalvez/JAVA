package enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.SimEntity.Robot;

import de.vogella.algorithms.dijkstra.engine.DijkstraAlgorithm;
import de.vogella.algorithms.dijkstra.model.Graph;
import de.vogella.algorithms.dijkstra.model.IEdge;
import de.vogella.algorithms.dijkstra.model.IVertex;
import de.vogella.algorithms.dijkstra.model.jfxmodel.Point3DEdge;
import de.vogella.algorithms.dijkstra.model.jfxmodel.Point3DVertex;
import enstabretagne.base.logger.Logger;
import enstabretagne.travaux_diriges.TD_corrige.MouvementCollisionAvoidance.Expertise.BorderAndPathGenerator;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.shape.Cylinder;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DiscoveredMap {
    private Robot robot;
    private List<IVertex> vertexList;
    private List<IEdge> edgeList;
    private HashMap<String, Point3D> vertexDB;
    private int resolution;
    private LinkedList<IVertex> path;
    private double robotUsefulRange;

    public DiscoveredMap(Robot robot, int resolution){
        this.robot = robot;
        this.vertexList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
        this.vertexDB = new HashMap<>();
        if (resolution != 1 && resolution != 2 && resolution != 5){
            System.err.println("Invalid resolution");
            System.exit(1);
        }
        this.resolution = resolution;
        this.robotUsefulRange = robot.getRange();
        switch (resolution) {
            case 2 :
                if ((int)Math.floor(robot.getRange()) % 2 != 0) {
                    this.robotUsefulRange += 1;
                }
                break;
            case 5 :
                if ((int)Math.floor(robot.getRange()) % 5 >= 3) {
                    this.robotUsefulRange += 5-((int)Math.floor(this.robot.getRange()))%5 ;
                }
                else {
                    this.robotUsefulRange -= ((int)Math.floor(this.robot.getRange()))%5;
                }
                break;
        }

    }

    public void discover(){
        Point3D center = closestPoint(robot.getPosition());
        for (int x = (int) (Math.ceil(center.getX()-robotUsefulRange)); x<=center.getX()+robotUsefulRange; x=x+resolution){
            for (int y = (int) (Math.ceil(center.getY()-robotUsefulRange)); y<=center.getY()+robotUsefulRange; y=y+resolution){
                if (find(new Point3D(x,y,0)) == null && (x>=2 && x<=52) && (y>=2 && y<=32)){
                    Point3DVertex point = new Point3DVertex(x,y,0);
                    this.vertexList.add(point);
                    this.vertexDB.put(point.getId(),point.getPoint());
                    this.robot.get3DRepresentation().attachGrid(point.getPoint(), resolution);
                    this.createEdge(point);
                }
            }
        }
    }

    private Point3D closestPoint(Point3D position) {
        int x = (int)Math.floor(position.getX());
        int y = (int)Math.floor(position.getY());
        if (resolution == 2){
            if (x%2 == 0){
                x += 1;
            }
            if (y%2 == 0){
                y += 1;
            }
        }
        else if (resolution == 5){
            if (x%10 != 0){
                if (x%10 <3){
                    x = 10*(x/10);
                }
                else if (x%10 < 8){
                    x = 10*(x/10 ) + 5;
                }
                else {
                    x = 10*(x/10 + 1);
                }
            }
            if (y%10 != 0){
                if (y%10 <3){
                    y = 10*(y/10);
                }
                else if (y%10 < 8){
                    y = 10*(y/10 ) + 5;
                }
                else {
                    y = 10*(y/10 + 1);
                }
            }
        }
        return new Point3D(x,y,0);
    }

    private void createEdge(Point3DVertex point) {
        Point3DVertex up = find(point.getPoint().add(0,resolution,0));
        if (isEdgeAllowed(point,up)){
            this.edgeList.add(new Point3DEdge(point,up));
            this.edgeList.add(new Point3DEdge(up,point));
            this.robot.get3DRepresentation().attachEdge(point,up);
        }
        Point3DVertex down = find(point.getPoint().add(0,-resolution,0));
        if (isEdgeAllowed(point,down)){
            this.edgeList.add(new Point3DEdge(point,down));
            this.edgeList.add(new Point3DEdge(down,point));
            this.robot.get3DRepresentation().attachEdge(point,down);
        }
        Point3DVertex right = find(point.getPoint().add(resolution,0,0));
        if (isEdgeAllowed(point,right)){
            this.edgeList.add(new Point3DEdge(point,right));
            this.edgeList.add(new Point3DEdge(right,point));
            this.robot.get3DRepresentation().attachEdge(point,right);
        }
        Point3DVertex left = find(point.getPoint().add(-resolution,0,0));
        if (isEdgeAllowed(point,left)){
            this.edgeList.add(new Point3DEdge(point,left));
            this.edgeList.add(new Point3DEdge(left,point));
            this.robot.get3DRepresentation().attachEdge(point,left);
        }

    }

    private boolean isEdgeAllowed(Point3DVertex point, Point3DVertex dest) {
        if (point!=null && dest!= null){
            return BorderAndPathGenerator.intervisibilityBetween(point.getPoint(),dest.getPoint(),robot.getObstacleBounds());
        }
        return false;
    }

    private Point3DVertex find(Point3D point) {
        for (IVertex v : this.vertexList){
            if (((Point3DVertex)v).getPoint().equals(point)){
                return (Point3DVertex)v;
            }
        }
        return null;
    }

    public void generatePath() {
        Graph graph = new Graph(this.vertexList,this.edgeList);
        DijkstraAlgorithm algo = new DijkstraAlgorithm(graph);
        algo.execute(find(closestPoint(new Point3D((Math.floor(robot.getPosition().getX())),(Math.floor(robot.getPosition().getY())),0))));
        this.path = algo.getPath(find(closestPoint(new Point3D((int)(Math.floor(robot.getInitialPosition().getX())),(int)(Math.floor(robot.getInitialPosition().getY())),0))));
        for (int i=0;i<path.size() - 1;i++){
            this.robot.get3DRepresentation().highlightPath(path.get(i), path.get(i+1));
        }
    }

    public Point3D getNext(){
        if (path != null && !path.isEmpty()){
            return this.vertexDB.get(path.remove(0).getId());
        }
        else {
            this.robot.stopReturn();
            return null;
        }
    }
}
