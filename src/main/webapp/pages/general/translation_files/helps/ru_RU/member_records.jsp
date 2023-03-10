<a NAME="top"></a>
<br><br>Записи об участниках - это определяемые администрацией блоки информации, относящиеся к конкретным пользователям. Есть много параметров для определения и управления записями об участниках. <br>
Записи об участниках используются для хранения множественной информации об участниках системы (т.е. множества различных значений по одному и тому же параметру, которые хранятся в разделе &quot;запичи об участнике&quot;). Это отличает записи об участнике от <a
	href="/do/member/manual?page=custom_fields"><u>настраиваемых полей</u></a>, где администрация системы создает блоки определенной информации о пользователе, но в случае записей об участнике один пользователь может иметь только одно значение по каждой записи, не более. <br>
<br>
В записях об участниках можно создавать много различных типов и полей. Простым примером могут служить обычные &quot;заметки&quot;: это просто список текстовых полей, которые можно было добавить по участнику. Теперь можно добавлять много типов полей для записи данных, и есть различные способы их отображения. 


<span class="admin"><br><br>Это руководство содержит небольшое меню <a href="#guidelines"><u>рекомендаций</u></a>, 
которое вы можете захотеть просмотреть, и содержит <a href="#example"><u>пример</u></a>, чтобы сделать использование записей об участнике более иллюстративным. 
<br><br><i>Где это найти?</i><br>
Записи об участнике можно найти на <a href="/do/member/manual?page=profiles"><u>странице личных данных</u></a>
участника > блок &quot;Данные участника&quot;. Вы можете настройки типы записей об участнике через &quot;Меню: Пользователи и группы > Типы записей об участнике&quot;.
<br><br>
<i>Как это работает?</i><br>
Просмотрите наши <a href="#guidelines"><u>рекомендации</u></a> по этому вопросу.
</span>
<span class="broker">
<br><br>Эти записи об участнике являются дополнительной информацией, связанной с каждым участником. Вы можете иметь разрешения к просмотру, редактированию и удалению этих записей. 
<br><br><i>Где это найти?</i><br>
Записи об участнике можно найти на <a href="/do/member/manual?page=profiles"><u>
странице личных данных</u></a> участника. Тип записи об участнике будет содержаться в &quot;Действия брокера для ...&quot;.<br>
Есть также поиск записей по типам записей об участнике в меню 
&quot;Брокерство&quot;. Например, база данных содержит стандартный тип записи
&quot;Заметки&quot; (Remarks) в меню Брокерство. 
В этом поиске вы можете <a href="#search_member_records"><u>искать</u> записи.
</span>
<hr>

<span class="admin"> <a name="guidelines"></a>
<h3>Рекомендации по определению записей об участнике</h3>
Чтобы создать записи об участнике, нужно произвести следующие действия: 
<ol>
	<li>Вначале подумайте, чего вы хотите от записи об участнике. Какую информацию вы хотите хранить? Можно ли это сделать просто через использование обычного
	<a href="/do/member/manual?page=custom_fields"><u>настраиваемого поля</u></a>?
	<li>Если вы желаете создать новый тип записи об участнике, вам нужно иметь соответствующие разрешения. Их можно найти в разделе &quot;Типы записей об участнике&quot;
	в административных <a href="/do/member/manual?page=groups#manage_group_permissions_admin_system"><u>
	разрешениях</u></a>; вам нужно выбрать галочкой функцию &quot;управление&quot; в анкете разрешений.
	<li>После этого создайте новый тип записи об участнике через &quot;Меню:
	Пользователи и группы > <a href="#member_record_types_list"><u>Записи об участнике</u></a>
	&quot; с помощью кнопки &quot;Добавить новый тип записи об участнике&quot;.
	<li>После того, как вы создали новый тип записи об участнике, на  <a
		href="#member_record_type_fields_list"><u>следующем экране</u></a> появятся возможности добавления полей в тип записи об участнике. Вы ДОЛЖНЫ добавить хотя бы одно поле в каждый тип записи об участнике, иначе тип не сможет хранить никакой информации и будет бесполезен. По некоторым полям вам нужно также создать возможные значения (см. <a href="#example"><u>пример</u></a> ).
	<li><b>Настроить разрешения:</b> После создания типа записи, определите, кто может просматривать, изменять и удалять записи об участнике, в &quot;Меню: Пользователи и группы > Группы разрешений&quot; блок &quot;Записи об участнике&quot;. Вы можете настроить эти функции для админ групп, а также для брокеров. 
	<li>Запись участника будет отображаться с кнопкой под <a
		href="/do/member/manual?page=profiles"><u> страницей личных данных участника</u></a>, в разделе &quot;Информация об участнике &quot; окна <a
		href="/do/member/manual?page=profiles#actions_for_member_by_admin"><u>Действия для...</u></a>. Оно будет доступно только для брокеров и админов. Здесь вы можете добавлять любое количество информации по записи об участнике. 
	<li>Если конфигурация записи об участнике содержит отмеченную опцию &quot;Отображать пунктом в меню&quot;, вы можете проводить поиск значений данной записи об участнике через главное
	&quot;Меню: Пользователи и группы&quot;.
	<li><b>Поиск:</b> Все записи участников можно искать через страницу
	&quot;действий&quot; под страницей личных данных участника. Вы также можете проводить поиск записей всех участников (не только относящихся к одному участнику) через меню. 
</ol>
<hr class="help">

<a name="example"></a>
<h3>Пример записи об участнике</h3>
Это описание относится к конкретному примеру типа записи об участнике, чтобы сформировать видение и понимание записей об участниках у пользователя более конкретным. Пример весьма обычен и, несомненно, возможна и лучшая конфигурация типа записи об участнике.
<ol>
	<li><b>Подумайте о том, чего вы хотите: </b> В этом примере вы создадим новый тип записи об участнике под названием "Обращения за помощью в работе с системой", чтобы отслеживать, как часто участники обращаются за помощью в работе с системой и с какими проблемами. <br>
	<br>
	<li><b>Настройте разрешения:</b> &quot;меню: пользователи и группы > группы разрешений&quot;, и используйте руководство по использованию этих окон, размещенное в соответствующих разделах по <a
		href="/do/member/manual?page=groups#manage_groups"><u>настройке разрешений</u></a>.<br>
	<br>
	<li><b>Создайте новый тип записи об участнике: </b> In &quot;Меню: Пользователи и группы >
	<a href="#member_record_types_list"><u>Записи об участнике</u></a>&quot; вам нужно нажать на кнопку &quot;добавить новый тип записи об участнике&quot;. В следующем <a
		href="#edit_member_record_type"><u>окне</u></a> вам нужно заполнить следующие поля: 
	<ul>
		<li><b>Название:</b> &quot;обращения за помощью&quot;
		<li><b>Название категории:</b> будет &quot;обращения за помощью&quot;.
		<li><b>Группы:</b> выберите группу участнков, для которой вы хотите использовать новый тип записи. Например, &quot;активные участники&quot;
		<li><b>Отображение результатов поиска:</b> т.к. мы не используем ее ни для какого статистического анализа, мы просто выберем &quot;Список-Таблица&quot;.
		<li><b>Отображать пунктом в меню:</b>Означает, что тип записи будет отображаться в главном меню по пути &quot;меню: пользователи и группы&quot;. На этой странице вы можете искать значения по записи с использованием различных поисковых настроек.
		<li><b>Редактируемое:</b> нет потребности изменять объекты после их создания, поэтому данный параметры выбирать не станем. 
	</ul>
	После этого мы нажимаем &quot;Выполнить&quot;, чтобы создать новый тип записи об участнике.
	Он отобразится в вашем списке <a href="#member_record_types_list"><u>Типы записи об участнике</u></a>. <br>
	<br>
	<li><b>Создайте поля в типе записи об участнике: </b> Вам нужно создать поля в записи об участнике, иначе запись не имеет никакого смысла. В списке <a
		href="#member_record_types_list"><u>Типы записей об участнике</u></a> вы должны нажать на
	<img border="0" src="/pages/images/edit.gif" width="16"
		height="16">&nbsp; значок редактирования, которая откроет окно <a
		href="#edit_member_record_type"><u>редактирования типа записи об участнике</u></a>. Используйте кнопку
	&quot;добавить новое настраиваемое поле&quot; для каждого нового поля, которое нужно создать. Это откроет экран <a href="#member_record_type_fields_list"><u>списка полей типа записи об участнике</u></a>.<br>
	Поля несколько упрощены, но это просто пример. <br>
	<ul>
		<li><b>поле даты:</b> нажимая на кнопку &quot;добавить новое настраиваемое поле&quot; в &quot;списке полей типа записи об участнике&quot;, мы переходим на экран создания <a
			href="/do/member/manual?page=custom_fields#edit_custom_fields"><u>нового настраиваемого поля</u></a>. Здесь мы заполняем следующие поля (не указанные поля формы не являются обязательными для функционирования нового поля):
		<ul>
			<li><b>название:</b> &quot;дата&quot;
			<li><b>тип данных:</b> &quot;дата&quot;
		</ul>
		Заполняйте информацию в других полях формы и нажмите &quot;выполнить&quot; для сохранения. <br>
		<b>ПРИМЕЧАНИЕ:</b> в действительности, создание поля даты не является обязательным, т.к. система автоматически сохраняет дату создания каждого ввода данных в запись участника, поэтому вы можете по ней проводить поиск. 
		<li><b>поле типа:</b> снова нажмите на кнопку &quot;добавить новое настраиваемое поле&quot; в &quot;список полей типа записи об участнике&quot;. Теперь заполните следующие поля: 
		<ul>
			<li><b>название:</b> &quot;тип&quot;
			<li><b>тип данных:</b> &quot;список&quot;
			<li><b>тип поля:</b> &quot;кнопка выбора&quot;
			<li><b>обязательное:</b> подлежит проверке. 
		</ul>
		Нажав "Выполнить", вы увидите снова окно &quot;список полей типа записи об участнике&quot;. Теперь вам нужно определить возможные значения для нового поля, сделайте это, нажав на <img border="0"
			src="/pages/images/edit.gif" width="16" height="16">&nbsp; значок редактирования в отображаемом поле &quot;типа&quot;.<br>
		Это переведет вас назад к форме &quot;редактирование настраиваемого поля&quot;. Здесь ниже нажмите на кнопку
		&quot;новое возможное значение&quot;, чтобы добавить новые значения 
		&quot;жалоба на другого участника&quot;, &quot;не знаю, как пользоваться программой&quot;
		и &quot;хочу сказать, что счастлив&quot;. Завершив, эти значения появятся в списке <a href="/do/member/manual?page=custom_fields#possible_values"><u>
		возможных значений</u></a>. В конце нажмите на кнопку &quot;назад&quot;, чтобы вернуться к списку полей. 
		<li><b>Поле примечания:</b> Наконец, мы добавим поле примечания аналогичным способом: 
		<ul>
			<li><b>название:</b> &quot;примечание&quot;
			<li><b>тип данных:</b> &quot;Строка&quot;
			<li><b>Тип поля:</b> &quot;расширенный редактор текста&quot;
		</ul>
		Теперь новый тип записи завершен. Он станет доступен в главном меню, когда вы повторно зайдете в систему.
	</ul>
	<li><b>добавить данные:</b> Теперь вы можете начать использовать тип записи. Под профилем участника в разделе &quot;Информация об участнике&quot; окна <a
		href="/do/member/manual?page=profiles#actions_for_member_by_admin"><u>Действия для...</u></a> будет специальная кнопка &quot;обращения за помощью&quot;, которая переведет вас в окно ввода данных для новой записи об участнике. 
		<li><b>Вы можете искать данные по записи об участнике через &quot;меню: пользователи и группы  > обращения за помощью&quot;:</b>
</ol>
<hr class="help">
</span>


<span class="admin">
<a NAME="member_records"></a>
<h3>Редактирование записи об участнике</h3>
Этот экран содержит <a href="#top"><u>записи об участнике</u></a> - данные
<a href="#edit_member_record_type"><u>списка</u></a>
по типу записи об участнике. <br>
Он отображает пользователя, создавшего запись, дату и содержание настраиваемое полей, определенных в &quot;( меню: пользователи и группы > <a href="#member_record_types_list"><u>Типы записей об участнике</u></a>  )&quot;.
Если после записи было определено как &quot;Редактируемое&quot;, 
то возможность изменения данных записи также доступна. <br>
Если у вас есть разрешения, вы также можете создать новую запись, нажав на кнопку
&quot;Выполнить&quot; под таблицей на странице с ярлыком &quot;Создать новую..&quot;
<hr class="help">
</span>

<span class="broker">
<a NAME="member_records"></a>
<h3>Редактирование записи об участнике</h3>
Экран содержит данные <a href="#top"><u>записи об участнике</u></a>.
Некоторые типы записей об участнике могут редактироваться. Эти записи можно изменить, выбрав кнопку &quot;Изменить&quot; редактируя поля и нажав на &quot;Выполнить&quot;.<br>
Если у вас есть разрешения, вы также можете создать новую запись, нажав на кнопку &quot;Выполнить&quot; под таблицей на странице с ярлыком &quot;Создать новую..&quot;
<hr class="help">
</span>

<span class="admin"> <a NAME="member_record_types_list"></a>
<h3>Список типов записей об участнике</h3>
На этом экране отображаются типы <a href="#top"><u>записей об участнике</u></a>.
<ul>
	<li>Нажимая на <img border="0" src="/pages/images/edit.gif" width="16"
		height="16">&nbsp; значок редактирования, параметры типа записи об участнике можно изменить. 
	<li>Нажав на <img border="0" src="/pages/images/delete.gif"
		width="16" height="16">&nbsp; значок удаления, тип записи об участнике можно удалить. Обратите внимание, что удалить можно только те типы записей об участнике, которые еще не использовались; как только в типе записи есть хоть под одному участнику, их удалить больше нельзя. 
	<li>Чтобы создать новый тип записи об участнике, нажмите на кнопку &quot;Выполнить&quot;
	рядом с &quot;Добавить новый тип записи об участнике&quot;. Если вы хотите создать новый тип записи, вы можете использовать эти <a
		href="#guidelines"><u> рекомендации</u></a>.
</ul>
<hr class="help">
</span>

<span class="admin broker"> <a NAME="remarks"></a>
<h3>Строчные записи об участнике</h3>
Информация, которую вы можете вводить на данном экране, это построчная &quot;<a href="#top"><u>
запись участника</u></a>&quot;. Большую часть времени это определять возможность добавления информации в страницу личных данных участника. Поля определяются администрацией. Чтобы добавить запись, заполните поля (поля с красной звездочкой являются обязательными) и нажмите &quot;Выполнить&quot;. Текущие записи об участнике содержатся на странице ниже. 
<hr class="help">
</span>

<span class="admin broker"> <a NAME="search_member_records"></a>
<h3>Поиск записей об участнике</h3>
Здесь вы можете искать записи об участнике, заполняя поля и нажав &quot;Поиск&quot;.
<ul>
	<li><b>Ключевые слова:</b> позволяют проводить поиск по любым полям типа <a
		href="#top"><u>записи об участнике</u></a>.
	<li><b>Имя пользователя:</b> и...
	<li><b>Участник:</b> позволяют вам искать записи по пользователю, к которому они относятся полем даты;  
<li><b>Дата создания:</b> может быть использована для поиска записей по дате их создания. Каждая запись об участнике уже имеет дату создания; вам не нужно создавать это поле самостоятельно.
</ul>
Дополнительно к этим полям есть любые настраиваемые поля, определенные вами в типе записи об участнике (в &quot;Меню: Пользователи и группы> <a
	href="#member_record_types_list"><u> Типы записей участников</u></a> &quot;.
<hr class="help">
</span>

<span class="admin broker">
<a NAME="search_records_of_member"></a>
<h3>Поиск записей одного участника</h3>
Здесь вы можете искать записи конкретного участника, заполнив поля и нажав на &quot;Поиск&quot;.
<ul>
	<li><b>Ключевые слова:</b> позволяют проводить поиск по любым полям записи.
	<li><b>Дата создания:</b> сохраняется по каждому вводу информации по записи; автоматически создается по каждому типу записи об участнике. 
	<li>Дополнительно к этим полям есть еще спецполя, определенные по типу записи данного участника, и назначаемые администратором. 
	<li>Вы можете ввести новые данные в записи данного участника, нажав на кнопку&quot;выполнить&quot;. Эта кнопка отмечена ярлыком &quot;создать новую &quot;, за названием типа записи об участнике. 
</ul>
<hr class="help">
</span>

<span class="broker admin"> <a NAME="member_records_search_results"></a>
<h3>Результаты поиска записей об участнике</h3>
Здесь отображены результаты поиска записей. Поля имени пользователя и названия участника отображаются в списке.
<ul>
	<li>Чтобы просмотреть всю запись, нажмите на <img border="0"
		src="/pages/images/view.gif" width="16" height="16"> &nbsp; значок просмотра.
	<li>Чтобы изменить, нажмите на <img border="0" src="/pages/images/edit.gif"
		width="16" height="16"> значок редактирования.
	<li>Чтобы удалить, нажмите на <img border="0" src="/pages/images/delete.gif"
		width="16" height="16"> значок удаления. &nbsp; &nbsp;
</ul>
<hr class="help">
</span>

<span class="admin"> <a NAME="edit_member_record_type"></a>
<h3>Изменить или создать тип записи об участнике</h3>
Здесь вы можете изменить структуру <a href="#top"><u>Записи об участнике</u></a>.
Чтобы изменить запись, выберите &quot;Изменить&quot;, отредактируйте поля и нажмите
&quot;Выполнить&quot;. Если вы создаете новый тип, вам не нужно нажимать на 
&quot;изменить&quot;. Для создания нового типа, просмотрите <a
	href="#guidelines"><u>рекомендации</u></a> и наш <a href="#example"><u>пример</u></a>
<ul>
	<li><b>Название:</b>
	<li><b>Название категории:</b> используется в интерфейсе пользователя и обычно является названием записи об участнике в множественном числе.
	<li><b>Описание:</b> текстовое описание цели и значения записи. Вы можете заполнить туда любую информацию.
	<li><b>Группы:</b> в этом списке вы выбираете группы, к которым будет использоваться данный тип записи. Если запись относится к группе пользователей, то новый тип записи об участнике будет виден с кнопкой в разделе &quot;Информация об участнике&quot; окна <a
		href="/do/member/manual?page=profiles#actions_for_member_by_admin"><u>Действия для ...</u></a> под профилем участника (доступен только админстраторам). 
	<li><b>Отображение результатов поиска:</b> позволяет вам выбрать, как данные будут отображаться в результатах поиска. Есть следующие варианты:
	<ul>
		<li><b>Секции:</b> каждый объект будет отображаться под предыдущим, разделенный линией. Это удобно для примечаний и пр. 
		<li><b>Список-таблица:</b> объекты отображаются в формате таблицы с колонками и строчками. Каждая запись является строкой в таблице.
	</ul>
	<li><b>Отображать пунктом в меню:</b> если выбирается эта опция, объект будет виден в меню в интерфейсе админа в разделе
	&quot;Пользователи и группы&quot;
	. Вы можете использовать этот объект меню для <a href="#search_member_records"><u>поиска</u></a>
	типов записей участников по конкретным значениям. 
	<li><b>Редактируемое:</b> если опция выбрана, данные записи можно изменять после создания (админами или брокерами). Если не выбрана, ее нельзя будет изменить после создания. Обычно типы примечаний не редактируются: после создания ее нельзя изменить.
</ul>
<br><br>В дополнение к изменению настроек записи, вы также должны создать и изменить &quot;Спецполя&quot; в окне <a
	href="#member_record_type_fields_list"><u>ниже</u></a>, иначе запись участника, созданная вами, будет пустой и бессмысленной. 
<hr class="help">

<a NAME="member_record_type_fields_list"></a>
<h3>Список полей типа записи об участнике</h3>
Здесь отображаются поля записи об участнике. Поля - это места, где сохраняются и индексируются записи. Чтобы запись участника была полезной, в ней должно быть как минимум одно поле.
<ul>
	<li>Чтобы создать новое настраиваемое поле, нажмите на кнопку &quot;Выполнить&quot; рядом с 
	&quot;Добавить новое настраиваемое поле&quot;.
	<li>Чтобы изменить порядок отображения полей, нажмите на кнопку рядом с 
	&quot;Изменить порядок полей&quot;. Эта кнопка доступна, если изменение порядка уместно. 
	<li>Чтобы отредактировать поле, нажмите на <img border="0" src="/pages/images/edit.gif"
		width="16" height="16"> &nbsp; значок редактирования.
	<li>Чтобы удалить поле, нажмите на <img border="0" src="/pages/images/delete.gif"
		width="16" height="16"> &nbsp; значок удаления.
</ul>
<hr class="help">
</span>


<div class='help'>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
</div>
