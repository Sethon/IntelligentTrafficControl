package com.project.geom;

import java.awt.geom.Line2D;

public interface RoadShape {
	double getLength();
	Line2D.Double getSegment(double minSegmentLength, int segment);
	default int getNumSegments(double minSegmentLength){
		double length = this.getLength();
		return (int)(length / minSegmentLength);
	}
}
