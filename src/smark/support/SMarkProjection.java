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
package smark.support;

import vtools.dataModel.expression.Path;
import vtools.dataModel.expression.Projection;

/**
 * A SMapProjection is a projection that keeps also the SMarkElement. Its label
 * is the label of the SMarkElement.
 */
public class SMarkProjection extends Projection
{
    private SMarkElement _element;

    public SMarkProjection(Path path, SMarkElement element)
    {
        super(path, "foo");
        _element = element;
        setLabel(element.getLabel());
    }

    public SMarkProjection(Path path, String projection)
    {
        super(path, "lola");
        throw new RuntimeException("This constructor should not be used for SMarkProjections");
    }

    public SMarkElement getElement()
    {
        return _element;
    }

    public void set_element(SMarkElement _element)
    {
        this._element = _element;
    }

    public SMarkProjection clone()
    {
        SMarkProjection sm = (SMarkProjection) super.clone();
        sm._element = _element;
        return sm;
    }



}
