#pragma once
#ifndef CIRCLE_H
#define CIRCLE_H 
#include"Shape.h"
#include<string>

namespace shape
{
	class Circle : public Shape
	{
	public: 
		Circle(); 
		Circle(int theRadius);
		void setRadius(int newRadius); 
		double getRadius(); 
		virtual double getArea(); 
	private: 
		int radius; 
	};
}
#endif // !CIRCLE_H

