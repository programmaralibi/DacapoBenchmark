/*
 
 Derby - Class org.apache.derbyTesting.system.langtest.utils.TestViews
 
 Licensed to the Apache Software Foundation (ASF) under one or more
 contributor license agreements.  See the NOTICE file distributed with
 this work for additional information regarding copyright ownership.
 The ASF licenses this file to You under the Apache License, Version 2.0
 (the "License"); you may not use this file except in compliance with
 the License.  You may obtain a copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 
 */
package org.apache.derbyTesting.system.optimizer.utils;

import javolution.util.FastTable;

/**
 * 
 * Class TestViews: List of views used in this test are added in this class
 * 
 */
public class TestViews {
	static FastTable dropViews = new FastTable();

	static FastTable createViews = new FastTable();

	public static void init() {
		setDropViews();
		setCreateViews();
	}

	public static void setCreateViews() {
		createViews
				.add("create view v8 as select all col1,col2,col3,col4,col5,col6,col7 from mytable8 union all select col1,col2,col3,col4,col5,col6,col7 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7 from mytable7 ");
		createViews
				.add("create view v8_2 as select all col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable7 ");
		createViews
				.add("create view v16 as select all col1,col2,col3,col4,col5,col6,col7 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7 from mytable15 ");
		createViews
				.add("create view v16_2 as select all col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable15 ");
		createViews
				.add("create view v32 as select all col1,col2,col3,col4,col5,col6,col7 from mytable32  union all select col1,col2,col3,col4,col5,col6,col7 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7 from mytable15  union all select col1,col2,col3,col4,col5,col6,col7 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7 from mytable17  union all select col1,col2,col3,col4,col5,col6,col7 from mytable18  union all select col1,col2,col3,col4,col5,col6,col7 from mytable19  union all select col1,col2,col3,col4,col5,col6,col7 from mytable20  union all select col1,col2,col3,col4,col5,col6,col7 from mytable21  union all select col1,col2,col3,col4,col5,col6,col7 from mytable22  union all select col1,col2,col3,col4,col5,col6,col7 from mytable23  union all select col1,col2,col3,col4,col5,col6,col7 from mytable24  union all select col1,col2,col3,col4,col5,col6,col7 from mytable25  union all select col1,col2,col3,col4,col5,col6,col7 from mytable26  union all select col1,col2,col3,col4,col5,col6,col7 from mytable27  union all select col1,col2,col3,col4,col5,col6,col7 from mytable28  union all select col1,col2,col3,col4,col5,col6,col7 from mytable29  union all select col1,col2,col3,col4,col5,col6,col7 from mytable30  union all select col1,col2,col3,col4,col5,col6,col7 from mytable31 ");
		createViews
				.add("create view v32_2 as select all col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable32  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable15  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable17  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable18  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable19  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable20  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable21  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable22  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable23  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable24  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable25  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable26  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable27  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable28  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable29  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable30  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable31 ");
		createViews
				.add("create view v42 as select all col1,col2,col3,col4,col5,col6,col7 from mytable32  union all select col1,col2,col3,col4,col5,col6,col7 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7 from mytable15  union all select col1,col2,col3,col4,col5,col6,col7 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7 from mytable17  union all select col1,col2,col3,col4,col5,col6,col7 from mytable18  union all select col1,col2,col3,col4,col5,col6,col7 from mytable19  union all select col1,col2,col3,col4,col5,col6,col7 from mytable20  union all select col1,col2,col3,col4,col5,col6,col7 from mytable21  union all select col1,col2,col3,col4,col5,col6,col7 from mytable22  union all select col1,col2,col3,col4,col5,col6,col7 from mytable23  union all select col1,col2,col3,col4,col5,col6,col7 from mytable24  union all select col1,col2,col3,col4,col5,col6,col7 from mytable25  union all select col1,col2,col3,col4,col5,col6,col7 from mytable26  union all select col1,col2,col3,col4,col5,col6,col7 from mytable27  union all select col1,col2,col3,col4,col5,col6,col7 from mytable28  union all select col1,col2,col3,col4,col5,col6,col7 from mytable29  union all select col1,col2,col3,col4,col5,col6,col7 from mytable30  union all select col1,col2,col3,col4,col5,col6,col7 from mytable31  union all select col1,col2,col3,col4,col5,col6,col7 from mytable54  union all select col1,col2,col3,col4,col5,col6,col7 from mytable55  union all select col1,col2,col3,col4,col5,col6,col7 from mytable56  union all select col1,col2,col3,col4,col5,col6,col7 from mytable57  union all select col1,col2,col3,col4,col5,col6,col7 from mytable58  union all select col1,col2,col3,col4,col5,col6,col7 from mytable59  union all select col1,col2,col3,col4,col5,col6,col7 from mytable60  union all select col1,col2,col3,col4,col5,col6,col7 from mytable61  union all select col1,col2,col3,col4,col5,col6,col7 from mytable62  union all select col1,col2,col3,col4,col5,col6,col7 from mytable63 ");
		createViews
				.add("create view v42_2 as select all col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable32  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable1  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable2  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable3  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable4  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable5  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable6  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable7  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable8  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable9  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable10  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable11  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable12  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable13  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable14  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable15  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable16  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable17  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable18  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable19  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable20  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable21  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable22  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable23  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable24  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable25  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable26  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable27  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable28  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable29  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable30  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable31  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable54  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable55  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable56  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable57  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable58  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable59  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable60  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable61  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable62  union all select col1,col2,col3,col4,col5,col6,col7,col8,col9 from mytable63 ");
		// Nested Views
		createViews
				.add("create view v_level1 as select all col1 as v_level1c1,col2 as v_level1c2,col3 as v_level1c3,col4 as v_level1c4,col5 as v_level1c5,col6 as v_level1c6 ,col7 as v_level1c7 from mytable1 union all select col1 as v_level1c1,col2 as v_level1c2,col3 as v_level1c3,col4 as v_level1c4,col5 as v_level1c5,col6 as v_level1c6 ,col7 as v_level1c7 from mytable2 ");
		createViews
				.add("create view v_level2 as select all col1 as v_level2c1,col2 as v_level2c2,col3 as v_level2c3,col4 as v_level2c4,col5 as v_level2c5,col6 as v_level2c6 ,col7 as v_level2c7 from mytable3 union all select col1 as v_level2c1,col2 as v_level2c2,col3 as v_level2c3,col4 as v_level2c4,col5 as v_level2c5,col6 as v_level2c6 ,col7 as v_level2c7 from mytable4 union all select v_level1c1 as v_level2c1,v_level1c2 as v_level2c2,v_level1c3 as v_level2c3,v_level1c4 as v_level2c4,v_level1c5 as v_level2c5,v_level1c6 as v_level2c6,v_level1c7 as v_level2c7 from v_level1 ");
		createViews
				.add("create view v_level3 as select all col1 as v_level3c1,col2 as v_level3c2,col3 as v_level3c3,col4 as v_level3c4,col5 as v_level3c5,col6 as v_level3c6 ,col7 as v_level3c7 from mytable5 union all select col1 as v_level3c1,col2 as v_level3c2,col3 as v_level3c3,col4 as v_level3c4,col5 as v_level3c5,col6 as v_level3c6 ,col7 as v_level3c7 from mytable6 union all select v_level2c1 as v_level3c1,v_level2c2 as v_level3c2,v_level2c3 as v_level3c3,v_level2c4 as v_level3c4,v_level2c5 as v_level3c5,v_level2c6 as v_level3c6,v_level2c7 as v_level3c7 from v_level2 ");
		createViews
				.add("create view v_level4 as select all col1 as v_level4c1,col2 as v_level4c2,col3 as v_level4c3,col4 as v_level4c4,col5 as v_level4c5,col6 as v_level4c6 ,col7 as v_level4c7 from mytable7 union all select col1 as v_level4c1,col2 as v_level4c2,col3 as v_level4c3,col4 as v_level4c4,col5 as v_level4c5,col6 as v_level4c6 ,col7 as v_level4c7 from mytable8 union all select v_level3c1 as v_level4c1,v_level3c2 as v_level4c2,v_level3c3 as v_level4c3,v_level3c4 as v_level4c4,v_level3c5 as v_level4c5,v_level3c6 as v_level4c6,v_level3c7 as v_level4c7 from v_level3 ");
		createViews
				.add("create view v_level5 as select all col1 as v_level5c1,col2 as v_level5c2,col3 as v_level5c3,col4 as v_level5c4,col5 as v_level5c5,col6 as v_level5c6 ,col7 as v_level5c7 from mytable9 union all select col1 as v_level5c1,col2 as v_level5c2,col3 as v_level5c3,col4 as v_level5c4,col5 as v_level5c5,col6 as v_level5c6 ,col7 as v_level5c7 from mytable10 union all select v_level4c1 as v_level5c1,v_level4c2 as v_level5c2,v_level4c3 as v_level5c3,v_level4c4 as v_level5c4,v_level4c5 as v_level5c5,v_level4c6 as v_level5c6,v_level4c7 as v_level5c7 from v_level4 ");
		createViews
				.add("create view v_level6 as select all col1 as v_level6c1,col2 as v_level6c2,col3 as v_level6c3,col4 as v_level6c4,col5 as v_level6c5,col6 as v_level6c6 ,col7 as v_level6c7 from mytable11 union all select col1 as v_level6c1,col2 as v_level6c2,col3 as v_level6c3,col4 as v_level6c4,col5 as v_level6c5,col6 as v_level6c6 ,col7 as v_level6c7 from mytable12 union all select v_level5c1 as v_level6c1,v_level5c2 as v_level6c2,v_level5c3 as v_level6c3,v_level5c4 as v_level6c4,v_level5c5 as v_level6c5,v_level5c6 as v_level6c6,v_level5c7 as v_level6c7 from v_level5 ");
		createViews
				.add("create view v_level7 as select all col1 as v_level7c1,col2 as v_level7c2,col3 as v_level7c3,col4 as v_level7c4,col5 as v_level7c5,col6 as v_level7c6 ,col7 as v_level7c7 from mytable13 union all select col1 as v_level7c1,col2 as v_level7c2,col3 as v_level7c3,col4 as v_level7c4,col5 as v_level7c5,col6 as v_level7c6 ,col7 as v_level7c7 from mytable14 union all select v_level6c1 as v_level7c1,v_level6c2 as v_level7c2,v_level6c3 as v_level7c3,v_level6c4 as v_level7c4,v_level6c5 as v_level7c5,v_level6c6 as v_level7c6,v_level6c7 as v_level7c7 from v_level6 ");
		createViews
				.add("create view v_level8 as select all col1 as v_level8c1,col2 as v_level8c2,col3 as v_level8c3,col4 as v_level8c4,col5 as v_level8c5,col6 as v_level8c6 ,col7 as v_level8c7 from mytable15 union all select col1 as v_level8c1,col2 as v_level8c2,col3 as v_level8c3,col4 as v_level8c4,col5 as v_level8c5,col6 as v_level8c6 ,col7 as v_level8c7 from mytable16 union all select v_level7c1 as v_level8c1,v_level7c2 as v_level8c2,v_level7c3 as v_level8c3,v_level7c4 as v_level8c4,v_level7c5 as v_level8c5,v_level7c6 as v_level8c6,v_level7c7 as v_level8c7 from v_level7 ");
		// Aggregate Views
		createViews
				.add("create view sum_view_8a(col1) as select sum(col1) as sum_view_8ac1 from mytable1 union all select col1 from mytable2 union all select col1 from mytable3 union all select col1 from mytable4 union all select col1 from mytable5 union all select col1 from mytable6 union all select col1 from mytable7 union all select col1 from mytable8 ");
		createViews
				.add("create view sum_view_8b(sum_view_8bc1) as select sum(col1 + col8) as sum_view_8bc1 from mytable1 union all select sum(col1 + col8) as sum_view_8bc1 from mytable2 union all select sum(col1 + col8) as sum_view_8bc1 from mytable3 union all select sum(col1 + col8)  as sum_view_8bc1 from mytable4 union all select sum(col1 + col8) as sum_view_8bc1 from mytable5 union all select sum(col1 + col8) as sum_view_8bc1 from mytable6 union all select sum(col1 + col8) as sum_view_8bc1 from mytable7 union all select sum(col1 + col8) as sum_view_8bc1 from mytable8 ");
		createViews
				.add("create view avg_view_8a(col1) as select avg(col1) as avg_view_8ac1 from mytable1 union all select col1 from mytable2 union all select col1 from mytable3 union all select col1 from mytable4 union all select col1 from mytable5 union all select col1 from mytable6 union all select col1 from mytable7 union all select col1 from mytable8 ");
		createViews
				.add("create view avg_view_8b(avg_view_8bc1) as select avg(col1 + col8) as avg_view_8bc1 from mytable1 union all select avg(col1 + col8) as avg_view_8bc1 from mytable2 union all select avg(col1 + col8) as avg_view_8bc1 from mytable3 union all select avg(col1 + col8)  as avg_view_8bc1 from mytable4 union all select avg(col1 + col8) as avg_view_8bc1 from mytable5 union all select avg(col1 + col8) as avg_view_8bc1 from mytable6 union all select avg(col1 + col8) as avg_view_8bc1 from mytable7 union all select avg(col1 + col8) as avg_view_8bc1 from mytable8 ");
		createViews
				.add("create view count_view_8a(col1) as select count(col1) as count_view_8ac1 from mytable1 union all select col1 from mytable2 union all select col1 from mytable3 union all select col1 from mytable4 union all select col1 from mytable5 union all select col1 from mytable6 union all select col1 from mytable7 union all select col1 from mytable8 ");
		createViews
				.add("create view count_view_8b(count_view_8bc1) as select count(col1 + col8) as count_view_8bc1 from mytable1 union all select count(col1 + col8) as count_view_8bc1 from mytable2 union all select count(col1 + col8) as count_view_8bc1 from mytable3 union all select count(col1 + col8)  as count_view_8bc1 from mytable4 union all select count(col1 + col8) as count_view_8bc1 from mytable5 union all select count(col1 + col8) as count_view_8bc1 from mytable6 union all select count(col1 + col8) as count_view_8bc1 from mytable7 union all select count(col1 + col8) as count_view_8bc1 from mytable8 ");
		createViews
				.add("create view max_view_8a(col1) as select max(col1) as max_view_8ac1 from mytable1 union all select col1 from mytable2 union all select col1 from mytable3 union all select col1 from mytable4 union all select col1 from mytable5 union all select col1 from mytable6 union all select col1 from mytable7 union all select col1 from mytable8 ");
		createViews
				.add("create view max_view_8b(max_view_8bc1) as select max(col1 + col8) as max_view_8bc1 from mytable1 union all select max(col1 + col8) as max_view_8bc1 from mytable2 union all select max(col1 + col8) as max_view_8bc1 from mytable3 union all select max(col1 + col8)  as max_view_8bc1 from mytable4 union all select max(col1 + col8) as max_view_8bc1 from mytable5 union all select max(col1 + col8) as max_view_8bc1 from mytable6 union all select max(col1 + col8) as max_view_8bc1 from mytable7 union all select max(col1 + col8) as max_view_8bc1 from mytable8 ");
		createViews
				.add("create view min_view_8a(col1) as select min(col1) as min_view_8ac1 from mytable1 union all select col1 from mytable2 union all select col1 from mytable3 union all select col1 from mytable4 union all select col1 from mytable5 union all select col1 from mytable6 union all select col1 from mytable7 union all select col1 from mytable8 ");
		createViews
				.add("create view min_view_8b(min_view_8bc1) as select min(col1 + col8) as min_view_8bc1 from mytable1 union all select min(col1 + col8) as min_view_8bc1 from mytable2 union all select min(col1 + col8) as min_view_8bc1 from mytable3 union all select min(col1 + col8)  as min_view_8bc1 from mytable4 union all select min(col1 + col8) as min_view_8bc1 from mytable5 union all select min(col1 + col8) as min_view_8bc1 from mytable6 union all select min(col1 + col8) as min_view_8bc1 from mytable7 union all select min(col1 + col8) as min_view_8bc1 from mytable8 ");

	}

	public static void setDropViews() {
		// simple Views
		dropViews.add("DROP VIEW v8");
		dropViews.add("DROP VIEW v8_2");
		dropViews.add("DROP VIEW v16");
		dropViews.add("DROP VIEW v16_2");
		dropViews.add("DROP VIEW v32");
		dropViews.add("DROP VIEW v32_2");
		dropViews.add("DROP VIEW v42");
		dropViews.add("DROP VIEW v42_2");
		// Nested Views
		dropViews.add("DROP VIEW v_level8");
		dropViews.add("DROP VIEW v_level7");
		dropViews.add("DROP VIEW v_level6");
		dropViews.add("DROP VIEW v_level5");
		dropViews.add("DROP VIEW v_level4");
		dropViews.add("DROP VIEW v_level3");
		dropViews.add("DROP VIEW v_level2");
		dropViews.add("DROP VIEW v_level1");
		// Aggregate Views
		dropViews.add("DROP VIEW sum_view_8a");
		dropViews.add("DROP VIEW sum_view_8b");
		dropViews.add("DROP VIEW avg_view_8a");
		dropViews.add("DROP VIEW avg_view_8b");
		dropViews.add("DROP VIEW count_view_8a");
		dropViews.add("DROP VIEW count_view_8b");
		dropViews.add("DROP VIEW max_view_8a");
		dropViews.add("DROP VIEW max_view_8b");
		dropViews.add("DROP VIEW min_view_8a");
		dropViews.add("DROP VIEW min_view_8b");

	}
}
