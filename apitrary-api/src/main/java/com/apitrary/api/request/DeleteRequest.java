/*
 * Copyright 2012 Denis Neuling 
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
package com.apitrary.api.request;

import com.apitrary.api.annotation.Method;
import com.apitrary.api.annotation.Path;
import com.apitrary.api.annotation.PathVariable;
import com.apitrary.api.annotation.Required;
import com.apitrary.api.common.HttpMethod;
import com.apitrary.api.response.DeleteResponse;

/**
 * <p>
 * DeleteRequest class.
 * </p>
 * 
 * @author Denis Neuling (denisneuling@gmail.com)
 * 
 */
@Method(HttpMethod.DELETE)
@Path("/${entity}/${id}")
public class DeleteRequest extends Request<DeleteResponse> {
	private static final long serialVersionUID = -8265792383976749317L;

	@Required
	@PathVariable("${entity}")
	private String entity;

	@Required
	@PathVariable("${id}")
	private String id;

	/**
	 * <p>
	 * Constructor for DeleteRequest.
	 * </p>
	 */
	public DeleteRequest() {
	}

	/**
	 * <p>
	 * Getter for the field <code>entity</code>.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * <p>
	 * Setter for the field <code>entity</code>.
	 * </p>
	 * 
	 * @param entity
	 *            a {@link java.lang.String} object.
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * <p>
	 * Getter for the field <code>id</code>.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getId() {
		return id;
	}

	/**
	 * <p>
	 * Setter for the field <code>id</code>.
	 * </p>
	 * 
	 * @param id
	 *            a {@link java.lang.String} object.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
