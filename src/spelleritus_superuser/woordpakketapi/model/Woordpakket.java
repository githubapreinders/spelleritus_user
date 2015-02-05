/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-11-17 18:43:33 UTC)
 * on 2015-01-08 at 09:34:25 UTC 
 * Modify at your own risk.
 */

package spelleritus_superuser.woordpakketapi.model;

import java.util.ArrayList;

/**
 * Model definition for Woordpakket.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the woordpakketapi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Woordpakket extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String contents;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String identifier;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getContents() {
    return contents;
  }

  /**
   * @param contents contents or {@code null} for none
   */
  public Woordpakket setContents(java.lang.String contents) {
    this.contents = contents;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public Woordpakket setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Woordpakket setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIdentifier() {
    return identifier;
  }

  /**
   * @param identifier identifier or {@code null} for none
   */
  public Woordpakket setIdentifier(java.lang.String identifier) {
    this.identifier = identifier;
    return this;
  }

  @Override
  public Woordpakket set(String fieldName, Object value) {
    return (Woordpakket) super.set(fieldName, value);
  }

  @Override
  public Woordpakket clone() {
    return (Woordpakket) super.clone();
  }

public ArrayList<String> getArrayList()
{
ArrayList<String>helper = new ArrayList<String>();

String[] string = this.contents.split("[\\s+,\\s+]");
for (int i = 0; i < string.length; i++)
{
	string[i].trim();
	if (!(string[i].equals("")))
	{
		helper.add(string[i].toLowerCase());
	}
}
	return helper;
}

}























