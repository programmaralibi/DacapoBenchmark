/*

   Derby - Class org.apache.derby.impl.sql.compile.QueryTreeNodeFastTable

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package	org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.sanity.SanityManager;

import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.error.StandardException;

import java.util.Enumeration;
import javolution.util.FastTable;

/**
 * QueryTreeNodeFastTable is the root class for all lists of query tree nodes.
 * It provides a wrapper for javolution.util.FastTable. All
 * lists of query tree nodes inherit from QueryTreeNodeFastTable.
 *
 */

abstract class QueryTreeNodeFastTable extends QueryTreeNode
{
	private FastTable			v = new FastTable();

	public final int size()
	{
		return v.size();
	}

	QueryTreeNode elementAt(int index)
	{
		return (QueryTreeNode) v.get(index);
	}

	final void addElement(QueryTreeNode qt)
	{
		v.add(qt);
	}

	final void removeElementAt(int index)
	{
		v.remove(index);
	}

	final void removeElement(QueryTreeNode qt)
	{
		v.remove(qt);
	}

	final Object remove(int index)
	{
		return((QueryTreeNode) (v.remove(index)));
	}

	final int indexOf(QueryTreeNode qt)
	{
		return v.indexOf(qt);
	}

	final void setElementAt(QueryTreeNode qt, int index)
	{
		v.add(index, qt);
	}

	void destructiveAppend(QueryTreeNodeFastTable qtnv)
	{
		nondestructiveAppend(qtnv);
		qtnv.removeAllElements();
	}

	void nondestructiveAppend(QueryTreeNodeFastTable qtnv)
	{
		int qtnvSize = qtnv.size();
		for (int index = 0; index < qtnvSize; index++)
		{
			v.add(qtnv.elementAt(index));
		}
	}

	final void removeAllElements()
	{
		v.clear();
	}

	final void insertElementAt(QueryTreeNode qt, int index)
	{
		v.add(index, qt);
	}

	/**
	 * Format this list as a string
	 *
	 * We can simply iterate through the list.  Note each list member
	 * is a QueryTreeNode, and so should have its specialization of
	 * toString defined.
	 *
	 * @return	This list formatted as a String
	 */
	public String toString()
	{
		if (SanityManager.DEBUG)
		{
			StringBuffer	buffer = new StringBuffer("");

			for (int index = 0; index < size(); index++)
			{
				buffer.append(elementAt(index).toString()).append("; ");
			}

			return buffer.toString();
		}
		else
		{
			return "";
		}
	}

	/**
	 * Accept a visitor, and call v.visit()
	 * on child nodes as necessary.  
	 * 
	 * @param v the visitor
	 *
	 * @exception StandardException on error
	 */
	public Visitable accept(Visitor v) 
		throws StandardException
	{
		Visitable		returnNode = v.visit(this);
	
		if (v.skipChildren(this))
		{
			return returnNode;
		}

		int size = size();
		for (int index = 0; index < size; index++)
		{
			setElementAt((QueryTreeNode)((QueryTreeNode) elementAt(index)).accept(v), index);
		}
		
		return returnNode;
	}
}
