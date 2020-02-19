#pragma once
#ifndef _SET_H
#define _SET_H

namespace myset
{
	template<class T> 
	class Set
	{
	public: 
		Set(); 
		Set(const Set<T>& other); 
		~Set(); 
		void operator = (const Set<T>& right_side);
		void add(T item); 
		void remove(T item); 
		int size() const; 
		bool contains(T item) const; 
		T *to_array() const; 
	private: 
		T * items; 
		int num_items; 
	};
}

#endif // !_SET_H
