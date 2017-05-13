/*
 *
 * Copyright 2016 Big Data Curation Lab, University of Toronto,
 * 		   	  	  	   				 Patricia Arocena,
 *   								 Boris Glavic,
 *  								 Renee J. Miller
 *
 * This software also contains code derived from STBenchmark as described in
 * with the permission of the authors:
 *
 * Bogdan Alexe, Wang-Chiew Tan, Yannis Velegrakis
 *
 * This code was originally described in:
 *
 * STBenchmark: Towards a Benchmark for Mapping Systems
 * Alexe, Bogdan and Tan, Wang-Chiew and Velegrakis, Yannis
 * PVLDB: Proceedings of the VLDB Endowment archive
 * 2008, vol. 1, no. 1, pp. 230-244
 *
 * The copyright of the ToxGene (included as a jar file: toxgene.jar) belongs to
 * Denilson Barbosa. The iBench distribution contains this jar file with the
 * permission of the author of ToxGene
 * (http://www.cs.toronto.edu/tox/toxgene/index.html)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package vtools.dataModel.expression;

import vtools.visitor.Visitable;
import vtools.visitor.Visitor;

public class IN extends BooleanExpression implements Visitable, Cloneable
{
    ValueExpression _val;

    Query _q;

    public IN(ValueExpression val, Query q)
    {
        _val = val;
        _q = q;
    }

    public Query getRelation()
    {
        return _q;
    }

    public ValueExpression getExpression()
    {
        return _val;
    }

    public boolean equals(Object o)
    {
        if (!(o instanceof IN))
            return false;
        if (!super.equals(o))
            return false;
        IN newObj = (IN) o;
        if (!(_q.equals(newObj._q)))
            return false;
        if (!(_val.equals(newObj._val)))
            return false;
        return true;
    }

    public IN clone()
    {
        IN newObj = (IN) super.clone();
        newObj._q = _q.clone();
        newObj._val = (ValueExpression) _val.clone();
        return newObj;
    }

    public Visitor getPrintVisitor()
    {
        return StringPrinter.StringPrinter;
    }
}
