// ============================================================================
//
// Copyright (c) 2006-2013, Talend Inc.
//
// This source code has been automatically generated by_Talend Open Studio for ESB
// / Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// ============================================================================
package routines;

public class Relational {

    /**
     * ISNULL( ) Indicates when a variable is the null value.
     * 
     * {talendTypes} boolean | Boolean
     * 
     * {Category} Relational
     * 
     * {param} Object(null)
     * 
     * {example} ISNULL(null)
     * 
     * 
     */
    public static boolean ISNULL(Object variable) {
        return variable == null;
    }

    /**
     * NOT( ) Returns the complement of the logical value of an expression.
     * 
     * {talendTypes} boolean | Boolean
     * 
     * {Category} Relational
     * 
     * {param} boolean(true)
     * 
     * {example} NOT(false)
     */
    public static boolean NOT(boolean expression) {
        return !expression;
    }
}
