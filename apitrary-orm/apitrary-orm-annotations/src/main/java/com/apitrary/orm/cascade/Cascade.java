/*
 * Copyright 2012-2013 Denis Neuling 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package com.apitrary.orm.cascade;

/**
 * <p>Cascade class.</p>
 *
 * @author Denis Neuling (denisneuling@gmail.com)
 * 
 */
public enum Cascade {

	NOP(0),
	SAVE(1)
//	,
//	UPDATE(2)
	,
	DELETE(3)
	;
	
	private int code;
	private Cascade(int code){
		this.code = code;
	}
	/**
	 * <p>Getter for the field <code>code</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCode(){
		return code;
	}
}
