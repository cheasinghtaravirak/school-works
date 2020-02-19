#pragma once
#ifndef RECTANGLE_H
#define RECTANGLE_H
#include"Shape.h"
namespace shape
{
	class Rectangle : public Shape
	{
	public: 
		Rectangle(); 
		Rectangle(double newWidth, double newHeight);
		double getWidth(); 
		double getHeight(); 
		void setWidth(double newWidth); 
		void setHeight(double newHeight);
		virtual double getArea(); 
	private: 
		double width; 
		double height; 
	};
}



#endif // !RECTANGLE_H


