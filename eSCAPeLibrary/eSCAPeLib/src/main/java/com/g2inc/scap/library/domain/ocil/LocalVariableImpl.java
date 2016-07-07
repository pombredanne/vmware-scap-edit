package com.g2inc.scap.library.domain.ocil;
/* ESCAPE Software Copyright 2010 G2, Inc. - All rights reserved.
*
* ESCAPE is open source software distributed under GNU General Public License Version 3.  ESCAPE is not in the public domain 
* and G2, Inc. holds its copyright.  Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:

* 1. Redistributions of ESCAPE source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
* 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the ESCAPE Software distribution. 
* 3. Neither the name of G2, Inc. nor the names of any contributors may be used to endorse or promote products derived from this software without specific prior written permission. 

* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
* INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
* IN NO EVENT SHALL G2, INC., THE AUTHORS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
* OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
* OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
* OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.

* You should have received a copy of the GNU General Public License Version 3 along with this program. 
* If not, see http://www.gnu.org/licenses/ for a copy.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.g2inc.scap.model.ocil.LocalVariable;
import com.g2inc.scap.model.ocil.Question;
import com.g2inc.scap.model.ocil.VariableSet;

public class LocalVariableImpl extends VariableImpl implements LocalVariable {
	
	public final static HashMap<String, Integer> VARIABLE_ORDER = new HashMap<String, Integer>();
	static {
		VARIABLE_ORDER.put("notes", 0);
		VARIABLE_ORDER.put("description", 1);
		VARIABLE_ORDER.put("set", 2);
		VARIABLE_ORDER.put("default_value", 3);
	}
		
	@Override
	public HashMap<String, Integer> getOrderMap() {
	  return VARIABLE_ORDER;
	}

	@Override
	public Question getQuestion() {
		return ocilDocument.getQuestion(element.getAttributeValue("question_ref"));
	}
	
	@Override
	public void setQuestion(Question question) {
		element.setAttribute("question_ref", question.getId());
	}

	@Override
	public String getQuestionRef() {
		return element.getAttributeValue("question_ref");
	}
	
	@Override
	public void setQuestionRef(String ref) {
		element.setAttribute("question_ref", ref);
	}
	
	@Override
	public List<VariableSet> getSetList() {
		return getApiElementList(new ArrayList<VariableSet>(), "set", VariableSetImpl.class);
	}

	@Override
	public void setSetList(List<VariableSet> list) {
		replaceApiList(list, getOrderMap(), "set");
	}

	@Override
	public Object getDefaultValue() {
		return getValue("default_value");
	}

	@Override
	public void setDefaultValue(Object value) {
		setValue("default_value", value);
	}

	@Override
	public void addSet(VariableSet set) {
		insertApiChild(set, getOrderMap(), -1);	
	}

}