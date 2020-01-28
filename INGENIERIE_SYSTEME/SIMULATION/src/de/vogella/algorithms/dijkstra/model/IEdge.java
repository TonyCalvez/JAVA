package de.vogella.algorithms.dijkstra.model;

public interface IEdge  {
  
  String getId();

  IVertex getDestination();
  
  IVertex getSource();
  
  double getWeight();
  
}