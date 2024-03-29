﻿<span class="admin">
<br><br>
Данные страницы предназначены для функций управления пользователями. 
Они могут использоваться для поиска участников, регистрации новых участников, просмотра пользователей онлайн или другой информации о состоянии участника.

<i>Где найти это?</i><br> 
Ниже представлена информация о доступных функциях и их местонахождении в системе:
<ul>
	<li><b>Поиск и создание новых участников:</b> проводится через &quot;Меню: Пользователи и группы > 
	Управление участниками&quot;. Это касается также поиска брокеров. 
	<li><b>Поиск и создание администраторов:</b> проводится через &quot;Меню: Пользователи и группы >
	Управление админами&quot;.
	<li><b><a href="#connected"><u>Пользователи онлайн</u></a>:</b> размещены в разделе 
	&quot;Меню: Пользователи и группы > Пользователи онлайн&quot;.
	<li><b><a href="#bulk_actions"><u>Действия к группе</u></a>:</b> доступны в разделе 
	&quot;Меню: Пользователи и группы > Действия к группе &quot;.
</ul>
</span>


<span class="broker">
<ul>
	<li><b>Создание/регистрация новых участников:</b> При условии, что у вас есть права доступа, этот раздел находится в 
	&quot;Меню: Брокерство > Зарегистрировать участника&quot;.
</ul>
</span>

<span class="member">
<ul>
	<li><b>Поиск участников:</b> Вы можете проводить поиск участников через &quot;Меню: Поиск >
	Участники&quot;.
</ul>
</span>
<hr>

<span class="admin">
<A NAME="create_user"></A>
<h3>Добавить участника</h3>
Здесь вы можете ввести информацию о новом участнике. Окно довольно 
простое; его поля, отображаемые в форме, зависят от конфигурации. <br>
Участник будет частью <a href="${pagePrefix}groups"><u> группы</u></a>, которая отображена в первом поле. 
<br><br>Если существует группа брокеров с правами доступа к группе участника, которого вы создаете, вы можете привязать (по желанию) 
участника к брокеру
из данной группы брокеров. Для этого следует отметить пункты верху формы под ярлыком
&quot;назначить брокера&quot;.
Вы можете указать пароль пользователя и выбрать, будет ли участник его непосредственно использовать, либо он должен будет его сменить при следующем входе в систему. 
<br><br>После заполнения данных у вас есть выбор сохранить участника и добавить нового участника
(кнопка &quot;Сохранить и добавить нового участника&quot;), или открыть напрямую
<a href="${pagePrefix}profiles"><u>страницу личных данных</u></a> нового участника (кнопка &quot;Сохранить и открыть личные данные&quot;).
<hr class="help">
</span>

<span class="broker">
<a name="create_user_for_broker"></a>
<h3>Брокерство - Добавить нового участника</h3>
Здесь вы можете ввести информацию о новом участнике. Окно довольно простое; его поля в форме зависят от конфигурации системы. <br>
Участник автоматически будет привязан к вам как к брокеру после того, как вы завершите регистрацию.
Вы можете указать пароль пользователя и отметить, может ли он использовать его или должен будет сменить пароль при следующем входе в систему. 
<br><br>После заполнения данных у вас будет выбор сохранить участника и добавить нового участника еще (кнопка &quot;Сохранить и добавить нового участника&quot;), или же открыть
непосредственно <a href="${pagePrefix}profiles"><u>страницу личных данных</u></a> нового участника (кнопка &quot;Сохранить и открыть личные данные&quot;).
<br><br>
При регистрации нового участника, вы можете получать автоматически <a href="${pagePrefix}brokering#commission"><u>комиссионные</u></a>,
в зависимости от правил, работающих в системе. Комиссия зависит от объема торговли участников, которых вы зарегистрировали.
<br>
Через &quot;Меню: Брокерство > Управление участниками&quot; вы можете управлять зарегистрированными вами участниками, и отслеживать их активность.
<hr class="help">
</span>


<span class="member">
<A NAME="search_member_by_member"></A>
<h3>Поиск участников</h3>
На данной странице вы можете производить поиск участников.
Поиск участников будет проходить по всем полям профиля пользователя. Вы можете использовать больше одного ключевого слова при поиске.<br>
При поиске участников (и объявлений) можно использовать специальные &quot;символы&quot;. Наиболее часто используемые - это:
<ul>
	<li><b>* звездочка </b>-  &quot;замена знака&quot; позволяет вам производить поиск по части слова. Например, поиск по "ко*" отобразит в результате всех участников с комбинацией "ко" в личных данных: Котов Александр Николаевич, пос. Коноша, и т.п. (последнее - наименование поселка в адресе).
	<li><b>- нет</b> Поиск по ключевому слову со знаком минус перед ключевым словом или символом &quot;нет&quot; перед ним и пробелом отобразит в результате объекты, не содержащие данного ключевого слова.
	<li><b>или</b> Поиск отобразит результаты, в которых будет присутствовать либо одно, либо другое ключевое слово после символа &quot;or&quot;.
	<li><b>и</b> Поиск отобразит результаты, в которых будет присутствовать оба слова перед и после символа  &quot;и&quot;.
</ul>

<hr class="help">
</span>

<span class="member"> 
<A NAME="search_member_result"></A>
<h3>Результаты поиска (участников)</h3>
Окно содержит результаты поиска участников в системе. Нажатие на &quot;номер счёта&quot; или &quot;Участник&quot; откроет <a href="${pagePrefix}profiles"><u>
профиль</u></a> выбранного участника.
Выбрав изображение, вы перейдете к укрупненному изображению в отдельном окне. 
<hr class="help">
</span>

<span class="admin"> 
<A NAME="search_member_by_admin"></A>
<h3>Поиск участников</h3>
На странице поиска участников (Меню: Пользователи и группы > Управление участниками) вы можете проводить поиск участников и регистрировать новых. 
<br><br>Если вы желаете найти конкретных участников, вы можете заполнить различные поля (ни одно из них не обязательно для заполнения). Если вы нажмете на кнопку &quot;Поиск&quot;
без указания каких-либо данных в полях, вы получите список всех участников системы.<br>
<ul>
	<li><b>Групповые фильтры:</b> Здесь вы можете указать <a href="${pagePrefix}groups#group_filters"><u>
	групповой фильтр</u></a >.
	<li><b><a href="${pagePrefix}groups"><u>Группа с разрешениями</u></a></b>
	<li><b>Имя брокера / брокер:</b> Введите номер счёта или имя брокера, и страница результатов отобразит участников, относящихся к данному брокеру. 
	<li><b>Дата активации От / До:</b> С данными полями дат вы можете проводить поиск по дате, с которой кто-то стал участником Системы. Вы можете использовать для указания дат календарь.  (<img border="0" src="/pages/images/calendar.gif" width="16" height="16">&nbsp;).
</ul>
<br><br>
Чтобы зарегистрировать нового участника, используйте выпадающий список внизу формы слева. Выберите группу, которой будет принадлежать создаваемый участник, и вам откроется
 <a href="#create_user"><u>форма регистрация нового участника</u></a>. 
<hr class="help">
</span>
 
<span class="admin">
<A NAME="admin_search_member_result"></A>
<h3>Результаты поиска (для поиска участников)</h3>
Окно содержит результат поиска участников. Выбирая имя пользователя или его номер счёта, вы перейдете на
 <a href="${pagePrefix}profiles"><u>страницу личных данных</u></a> данного участника. Выбирая кнопку "назад" на странице профиля, вы вернетесь на страницу результатов поиска. 
<br><br>У вас есть возможность печати результатов поиска, нажав значок печати 
(<img border="0" src="/pages/images/print.gif" width="16" height="16">&nbsp;)
вверху окна справа, рядом со значком помощи. Это сформирует версию для печати в новом окне для предварительного просмотра и печати.
<br><br>Еще одна возможность - это скачать результаты поиска в файл, нажав значок "сохранить" (<img border="0" src="/pages/images/save.gif" width="16" height="16">&nbsp;).
Результаты будут сгружены в  <a href="${pagePrefix}loans#csv"><u>формате CSV</u></a>, содержащем все поля, существующие в <a
	href="${pagePrefix}profiles"><u>странице личных данных</u></a> (включая многие поля, невидимые в окне результатов поиска). <br>
В <a href="${pagePrefix}settings#local"><u>местных настройках</u></a> вы можете указать, желаете ли вы, чтобы наименования полей отображались в заголовке (шапке) колонок.
<br><br>Примечание: В функции отчетов вы сможете получить более детальные 
<a href="${pagePrefix}reports#member_lists"><u>списки участников</u></a>.
Например, списки участников с дополнительными данными в виде состояний счетов и числу объявлений, которые можно найти в функции отчетов. 
<hr class="help">
</span>

<span class="broker admin"> <a name="search_pending_member"></a>
<h3>Поиск вступающих участников</h3>
Вступающие участники - это участники, которые зарегистрировались, но еще не подтвердили свою регистрацию ответом на специальное e-mail-подтверждение. Только после подтверждения участник сможет войти в систему. <br>
После истечения определенного времени, вступающие участники будут автоматически удалены из системы (и из списка). Все три способа регистрации (самостоятельная регистрация участником на главной внешней странице входа в систему, регистрация брокером и администратором) могут настроены с обязательным требованием подтверждения по e-mail. <br>
На этой странице вы можете проводить поиск вступающих участников. Вы можете искать по имени участника, группе и периоду регистрации. <br>
Поиск по группе означает не то, что участники относятся к этой группе, а то, что после ответа на e-mail-подтверждение регистрации, они станут частью данной группы. <br>
</span>
<span class="admin"> Вы также можете проводить поиск по брокеру. Это означает, что поиск только отобразит вам участников, зарегистрированных выбранным брокером. Обратите внимание, что эта функция работает, только если участники, регистрируемые брокером, должны проводить подтверждение ответом на электронное письмо. Эта функция определяется в разделе <a href="${pagePrefix}groups#group_registration_settings"><u>Настройки регистрации</u></a>. Максимальный период подтверждения регистрации определяется во внутренних настройках системы.<br>
<br>
<hr class="help">
</span>

<span class="broker admin"> <a name="search_pending_member_result"></a>
<h3>Список вступающих участников</h3>
Окно содержит результат поиска вступающих участников. Будьте внимательны, т.к. эти участники не являются
&quot;активными&quot; участниками (они не могут входить в систему и не видны в ней). Только в редких случаях вы можете удалять участника из списка. Удаление вступающего участника будет означать, что он не сможет подтвердить свою регистрацию. <br>
Если вы нажмете значок редактирования данных участника, вы сможете просмотреть и изменить данные профиля и, при необходимости, направить повторный запрос на подтверждение регистрации. 
<hr class="help">
</span>

<span class="broker admin"> <a name="pending_member"></a>
<h3>Данные вступающих участников</h3>
На этой странице вы можете просмотреть и изменить личные данные участника и, при  необходимости, направить еще раз запрос на подтверждение регистрации по e-mail.
<hr class="help">
</span>

<span class="admin">
<a name="search_admin"></a>
<h3>Поиск админов</h3>
На странице поиска админов (Меню: Пользователи и группы > Управление админами) вы можете искать администраторов, а также регистрировать новых. 
<br><br>Форма очень проста: вы можете указать просто ключевое слово и/или ограничить поиск определенной группой админов. Если вы просто нажмете на кнопке &quot;Поиск&quot;
без указание какой-либо информации в полях, вы получите список всех администраторов.<br>
<br><br>Чтобы зарегистрировать нового администратора, используйте выпадающий список внизу формы. <a
	href="${pagePrefix}groups#admin_groups"><u>Выберите группу админов</u></a>, к которой будет принадлежать создаваемый админ, и вам откроется <a href="#create_user"><u>форма регистрации новых администраторов</u></a> .
</span>

<span class="admin">
<a name="search_admin_result"></a>
<h3>Результаты поиска (поиск админов)</h3>
Таблица содержит результаты поиска админов. Вы можете нажать на имени пользователя админа или его реальном имени, чтобы получить подробные данные об администраторе. 
<hr class="help">
</span>

<span class="admin">
<a name="create_admin"></a>
<h3>Регистрация нового администратора</h3>
Здесь вы можете зарегистрировать нового администраторв.
Мы
<b>настойчиво</b>
рекомендуем, чтобы все работники администрации имели каждый собственную учетную запись и имя пользователя, т.е. чтобы под одной учетной записью не работало сразу несколько человек. Это позволит более легко управлять правами доступа, отслеживать возможные ошибки и закрывать учетные записи в случае увольнения человека. 
<br><br>Форма очень проста. 
Любое поле с красной звездочкой <b>(*)</b> обязательно для заполнения. 
<br><br><br><br>После заведения данных, у вас будет возможность сохранить профиль админа, и завести нового (кнопка &quot;Сохранить и добавить нового админа&quot;) или открыть напрямую <a href="${pagePrefix}profiles"><u>страницу личных данных</u></a> нового админа (кнопка &quot;Сохранить и открыть личные данные&quot;).
<hr class="help">
</span>

<span class="admin">
<a name="connected"></a>
<h2>Пользователи онлайн</h2>
На странице пользователей онлайн (Меню: Пользователи и группы > Пользователи онлайн) вы увидите обзор пользователей (участников, админов, брокеров), которые в настоящий момент находятся в системе (работают в ней онлайн).

<hr>
</span>

<span class="admin">
<A NAME="connected_users"></A>
<h3>Пользователи онлайн</h3>
Окно позволяет вам указать, какие <a href="${pagePrefix}groups#group_categories"><u>
группы</u></a> пользователей онлайн вы желаете видеть в окне ниже. Выпадающий список &quot;показать&quot; позволяет вам выбрать между
&quot;админами&quot;, &quot;брокерами&quot;, &quot;участниками&quot; и &quot;
<a href="${pagePrefix}operators"><u>операторами</u></a>&quot;.<br>
Нажмите на кнопку &quot;выполнить&quot;, чтобы отобразить результаты. 
<hr class="help">
</span>
 
<A NAME="connected_users_result"></A>
<span class="admin">
<h3>Результаты поиска пользователей онлайн </h3>
Данная функция отобразит список пользователей онлайн согласно вашего выбора в окне выше. <br>
В списке вы можете нажать на участника, чтобы открыть его страницу личных данных. Админы с правами доступа (разрешениями) могут разъединить пользователя, нажав значок удаления
(<img border="0"
	src="/pages/images/delete.gif" width="16" height="16">&nbsp;) в последней колонке. 
</span>
<span class="member">
<h3>Результаты поиска операторов онлайн</h3>
Функция открывает список <a href="${pagePrefix}operators"><u>
операторов</u></a> онлайн.<br>
В списке вы можете нажать на оператора, чтобы открыть его страницу личных данных. Если у вас есть права доступа (разрешения), вы можете разъединить оператора, нажав значок удаления (<img border="0" src="/pages/images/delete.gif" width="16" height="16">&nbsp;)
в последней колонке. </span>
<hr class="help">

<span class="admin">
<a name="bulk_actions"></a>
<h2>Действия к группе</h2>
Эта функция (Меню: Пользователи и группы > Действия к группе) позволяет администратору выполнять действия для целой группы участников. 

<hr>
</span>

<span class="admin">
<A NAME="bulk_actions_filter"></A>
<h3>Фильтр участников для действия</h3>
Окно позволяет адмнистратору указать группу участников, по которым 
<a href="#bulk_actions"><u>действие к группе</u></a> будет выполнено. Поля комбинируются с логическим поиском И, поэтому, убедитесь, что вы не указываете слишком много данных, т.к. это может привести к отсутствию результатов поиска.
<ul>
	<li><b>Групповой фильтр:</b> укажите <a href="${pagePrefix}groups#group_filters"><u>
	групповой фильтр</u></a>.
	<li><b>Группа с разрешениями:</b> Укажите <a href="${pagePrefix}groups"><u>
	группу</u></a>. Убедитесь, что указанная группа не конфликтует с групповым фильтром, указанным выше. 
	Обычно указывают либо групповой фильтр, либо группу, но не вместе. 
	<li><b>Номер счёта брокера/Брокер:</b> если вы хотите произвести действие по всем участникам одного брокера, укажите брокера здесь, введя его номер счёта или его имя. 
	<li><b>...:</b>остальные поля формы отображают некоторые <a href="${pagePrefix}custom_fields"><u>
	настраиваемые поля</u></a>, определяемые на странице личных данных участника.  
</ul>
Указав критерии поиска для действия к группе, вы можете просмотреть, какие участники включены в результаты, нажав кнопку &quot;просмотр&quot;внизу формы справа. Это откроет список включенных участников. 
<hr class="help">
</span>

<span class="admin"> <A NAME="bulk_action"></A>
<h3>Действие</h3>
Здесь определяется <a href="#bulk_actions"><u>действие к группе </u></a>. У вас есть следующие возможности:
<ul>
	<li><b>Сменить группу:</b> Это приведет к смене группы разрешений выбранных пользователей.<br>
	Выбрав эту опцию, у вас программа запросит указать новую <a
		href="${pagePrefix}groups"><u>группу с разрешениями</u></a>, и комментарий. Нажав &quot;выполнить&quot;, все участники из выбранного списка будут переведены в новую группу. 
	<li><b>Сменить брокера:</b> Данное действие сменит <a
		href="${pagePrefix}brokering"><u>брокера</u></a> выбранной группы. <br>
	Выбрав эту опцию, у вас программа запросит указать имя брокера или его номер счёта (указав одно из них, остальные заполнятся автоматически).<br>
	Выбрав опцию &quot;Приостановить отчисления брокеру &quot;, вы инициируете остановку всех текущих и открытых <a href="${pagePrefix}brokering#commission"><u>брокерских вознаграждений</u></a>. Вы можете использовать эту опцию, если считаете, что новый брокер не имеет прав получать брокерское вознаграждение от действий предыдущего брокера. <br>
	Вы должны также ввести комментарий. После нажатия кнопки &quot;выполнить&quot;
	все выбранные участники будут переведены к новому брокеру. 
</ul>
<hr class="help">

<A NAME="import_members"></A>
<h3>Импорт участников</h3>
С данной функцией вы можете импортировать участников (личные данные) и по выбору устанавливать начальное состояние счёта и генерировать первоначальные платежи от или в пользу системного счета. Числа и даты должны быть отформатированы согласно внутренних настроек. <br>
Поля определяются наименованием колонки. Наименования колонки чувствительны к регистру, и должны быть указаны в шапке (первой линии). Колонки могут быть в любом порядке
(нет колонки один или два, если ее наименование корректно, импорт сработает).
Если поле является необязательным, вы можете его опустить в виде целой колонки, или оставить ее пустой. <br>
В системе поддерживаются следующие колонки/поля.
<ul>
	<li>Участник (обязательно)<br>
	Полное имя участника.
	<li>номер счёта (обязательно)<br>
	Уникальный номер счёта, означает, что оно будет выдавать ошибку, если такй номер счёта уже существует в системе. В случае если номера счетов генерируются автоматически, колонка не нужна, и Система самостоятельно сгенерирует их.
       <li>дата создания (по выбору)<br>
	Если опция выбрана, то дата в личных отчетах участника (с момента его регистрации) будет установлена по этой дате. Если дата на установлена, будет проставлена колонка текущей даты (импортирования). 
	<li>пароль (по выбору)<br>
	Стандартный пароль участника. Участники должны будут сменить его при первом входе в систему. 
	<li>Эл.почта (обязательно или по выбору согласно настроек Системы). Должен быть сформирован в правильном формате e-mail адресов.
	<li>состояние счёта(по выбору)<br>
	Изначальное состояние счёта. Используется только, если выбран тип счета. Если вы ввыбираете тип счета, вы можете указать типы платежей (от участника на систему с отрицательным состояние счёта или от системы на участника для положительного состояние счёта).<br>
	Если вы используете данную опцию, вы должны убедиться, что на счете дебета есть достаточная сумма.
	<li>Нижний предел состояния счёта (по выбору)<br>
	Если поле пустое, по счету будут сформированы настройки по умолчанию для группы.
	<li>Верхний предел состояния счёта (по выбору)<br>
	Если поле пустое, по счету будут сформированы настройки по умолчанию для группы
	<li>внутренне наименование настраиваемого поля (по выбору)<br>
	Внутреннее название настраиваемого поля, связанного с выбранной группой. Требуется активация. Если поле - это список (данных), импорт участника пройдет успешно, если в нем заведено существующее значение. Например, если у вас есть настраиваемое поле  &quot;регион&quot; с тремя возможными регионами &quot;центр&quot;
	&quot;юг&quot; and &quot;север&quot; участники с другими регионами импортированы не будут. 
	Участники с пустым полем региона будут импортированы (если поле не установлено как
	&quot;обязательное&quot;).
	<li>тип записи об участнике. внутреннее название настраиваемого поля (по выбору<br>
	Значения записей участника. Например в стандартной базе данных это
	&quot;Запись комментарий&quot;. Где комментарий - это поле типа записи. Поле должно иметь внутреннее название (указанное в поле настроек).<br>
	Убедитесь, что если вы хотите импортировать тип записи поля, которые определены для данной записи, их нужно указать в файле csv (как отдельную колонку).
	</ul>
<hr class="help">


<A NAME="imported_members_summary"></A>
<h3>Обзор импортированных участников</h3>
Страница содержит обзор импортированных участников. На данной стадии ничего еще не было обработано. Вы можете выбрать ссылку (номер), чтобы увидеть статус успешно или неуспешно импортированных участников (или просмотреть оба статуса, выбрав значение после 
&quot;Все участники&quot;).<br>
Если вы выберете &quot;Импорт&quot; успешные участники будут импортированы.
Это хороший способ просмотра статуса импортированных участников. 
<hr class="help">


<A NAME="imported_member_details"></A>
<h3>Поиск импортированных участников</h3>
В данном окне вы можете искать в импортированном списке конкретных участников. Вы можете искать как по номеру строки, так и по имени участника. Вариант поиска по участнику возможен как по имени пользователя, так и наименованию компании. <br>
<hr class="help">


<A NAME="imported_member_details_result"></A>
<h3>Результаты поиска импортированных участников</h3>
Окно содержит результаты импорта. В случае ошибок при импорте, оно сообщит о типе ошибки (напр., об отсутствии поля, номере счёта, которое уже используется и пр.), и в случае успешного импорта оно отобразит нижний предел состояния счёта и состояние счёта.<br>
Чтобы обработать участников, вернитесь и нажмите на кнопку  &quot;Импорт&quot;.
</span>

<span class="member">
<hr>
<br><br><A NAME="contacts"></A>
<h3>Контакты</h3>

<span class="member">
<br><br>Данный раздел позволяет вам управлять своими контактами. 

В списке контактов (Меню: Моя почта > Контакты) вы можете производить различные действия, выбирая с помощью мышки следующие опции: 
<ul>
	<li><b>Номер счета - Имя участника:</b> Открыть страницу личных данных участника.
	<li><b>Адрес эл.почты:</b> направить данному участнику e-mail.
	<li><b><img border="0" src="/pages/images/edit.gif" width="16" height="16">&nbsp;</b>
	Просмотр/Добавить/редактировать дополнительную информацию о данном участнике. 
	<li><b><img border="0" src="/pages/images/edit_gray.gif" width="16"
		height="16">&nbsp;</b> У иконки нет цвета, и это означает, что информационное поле не содержит никакой информации. 
	<li><b><img border="0" src="/pages/images/delete.gif" width="16"
		height="16">&nbsp;</b> Удалить участника из списка контактов. 
</ul>
Вначале у вас не будет никаких контактов в списке. Мы можете добавлять контакты двумя способами: 
<ul>
	<li>Используя окно &quot;<a href="#add_contact"><u>добавить новый контакт</u></a> &quot;
	ниже.
	<li>При первом проведении <a href="${pagePrefix}#search_member_by_member"><u>поиска участников</u></a> (&quot;Меню: Поиск&quot;). В списке результатов поиска вы можете войти на 
	<a href="${pagePrefix}profiles"><u>страницу личных данных</u></a> участника,
	нажав на имя участника. Нажмите на &quot;добавить в контакты&quot;, чтобы добавить участника в свой список контактов.  
</ul>
<hr class="help">

<br><br><A NAME="add_contact"></A>
<h3>Добавить в контакты</h3>
Здесь вы можете добавлять новые контакты в свой список контактов. Вы можете сделать это, введя имя пользователя или его номер счёта в поля (с функцией автозаполнения) и нажать на кнопку &quot;Выполнить&quot;.
<hr class="help">

<br><br><A NAME="contact_note"></A>
<h3>Примечание к контакту</h3>
В этой странице вы можете вставить дополнительную информацию о пользователе. Эта информация будет доступна для просмотра только вам, и будет удалена, если вы удалите участника из списка контактов.
<hr class="help">

<a name="contact_us"></a>
<h3>Контакты</h3>
Страница содержит контактные данные Системы, которыми вы можете воспользоваться, чтобы связаться с администрацией. 
<hr class="help">
</span>

<br><br>


