/************************************************************************
 * Strathclyde Planning Group,
 * Department of Computer and Information Sciences,
 * University of Strathclyde, Glasgow, UK
 * http://planning.cis.strath.ac.uk/
 * 
 * Copyright 2007, Keith Halsey
 * Copyright 2008, Andrew Coles and Amanda Smith
 *
 * (Questions/bug reports now to be sent to Andrew Coles)
 *
 * This file is part of JavaFF.
 * 
 * JavaFF is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * JavaFF is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with JavaFF.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ************************************************************************/

package javaff.data.temporal;

import javaff.data.strips.Proposition;
import javaff.planning.TemporalMetricState;

import java.util.Set;
import java.util.Iterator;

public class EndInstantAction extends SplitInstantAction 
{

	public SplitInstantAction getSibling()
    {
		return parent.startAction;
	}

	public void applySplit(TemporalMetricState ts)
    {
		Set is = parent.invariant.getConditionalPropositions();
		
		Iterator iit = is.iterator();
		while (iit.hasNext())
		{
			ts.invariants.remove(iit.next());
		}
		ts.openActions.remove(parent);
		ts.actions.remove(this);
		ts.actions.add(getSibling());
	}

	public boolean exclusivelyInvariant(Proposition p)
    {
		return !parent.endCondition.getConditionalPropositions().contains(p) || !parent.endEffect.getAddPropositions().contains(p) || !parent.endEffect.getDeletePropositions().contains(p);
	}
}
