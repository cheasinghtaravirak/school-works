#pragma once
#ifndef SHAPE_H
#define SHAPE_H

#include<string>
using namespace std; 
namespace shape 
{

	class Shape
	{
	public: 
		Shape();
		Shape(string name); 
		string getName(); 
		void setName(string newName); 
		virtual double getArea() = 0; 
	private: 
		string name; 
	};
}
#endif // !SHAPE_H
