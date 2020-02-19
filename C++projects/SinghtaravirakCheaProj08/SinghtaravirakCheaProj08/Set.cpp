#pragma once
#include "stdafx.h"
#include "Set.h"

namespace myset
{
	template<class T>
	Set<T>::Set() : items(NULL), num_items(0)
	{

	}
	template<class T> 
	Set<T>::Set(const Set<T>& other)
	{
		num_items = other.num_items; 
		items = other.to_array(); 
	}
	template<class T> 
	Set<T>::~Set()
	{
		if (items != NULL)
			delete[] items; 
	}
	template<class T> 
	void Set<T>::operator = (const Set<T>& right_side)
	{
		if (items != NULL)
			delete[] items; 
		num_items = right_side.num_items; 
		items = right_side.to_array(); 
	}
	template<class T> 
	void Set<T>::add(T item)
	{
		if (!contains(item))
		{
			T *new_items = new T[num_items + 1]; 
			for (int i = 0; i < num_items; i++)
			{
				new_items[i] = items[i]; 
			}
			new_items[num_items] = item; 
			if (num_items > 0)
			{
				delete[] items;
			}
			num_items++; 
			items = new_items; 
		}
	}
	template<class T> 
	void Set<T>::remove(T item)
	{
		if (contains(item))
		{
			T *copy = NULL;
			if (num_items > 1)
			{
				copy = new T[num_items - 1]; 
				for (int i = 0, j = 0; i < num_items; i++)
				{
					if (items[i] != item)
					{
						copy[j] = items[i]; 
						j++; 
					}
				}
			}
			delete[] items;
			items = copy;
			num_items--;

		}

	}
	template<class T>
	int Set<T>::size() const
	{
		return num_items; 
	}
	template<class T> 
	bool Set<T>::contains(T item) const
	{
		for (int i = 0; i < num_items; i++)
		{
			if (items[i] == item)
				return true; 
		}
		return false; 
	}
	template<class T> 
	T *Set<T>::to_array() const
	{
		T *copy = NULL;
		if (num_items > 0)
		{
			copy = new T[num_items]; 
			for (int i = 0; i < num_items; i++)
			{
				copy[i] = items[i]; 
			}
		}
		return copy; 
	}
}