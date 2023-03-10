<div style="page-break-after: always;">
<a name="top"></a>
<br><br>ローンは、Cyclosに基本的なクレジット機構を与えます。Cyclosは、スケジュールされた支払い(割賦金)と利子という不可欠な特質をサポートし、管理者が様々な手数料を適用できるようにします。<br> ローンはCyclos内部か外部で返済できます。後者の場合、ローンはCyclos内で管理者により「放棄される」必要があるでしょう。ローンは、1回の支払いか複数のローン支払いからなります。 ローンが複数の支払いからなる場合、各々が満期日と状態を持ちます。ローンは、個々のメンバーまたは「ローングループ」のいずれかに対して認可できます。ローングループは、複数のメンバーからなるグループです。そのメンバーの1人に、ローンを処理する権限を割り当てることができます。これはマイクロファイナンスのプロジェクトにとって一般的な機能です。<span class="admin"> <br><br><i>見つけ方/動かし方</i><br> Cyclos内でローンを使うためには、以下のステップを行ってください:
<ol>
	<li><b>移転タイプを作成する:</b> ローンを使えるようになる前に、ローンのための適切な<a href="${pagePrefix}account_management#transaction_types"><u>取引タイプ</u></a>が作成されなければなりません。各ローンは、それ独自の取引タイプを持たなければならないからです。<br> 一般に2つのローン取引タイプを作成しなければなりません。つまり、メンバーへのローンを(システム口座から)提供するものと、メンバーがローンを返済するために使う別の(したがって、メンバーからシステム口座への)タイプのものです。後者を指定せずに前者を作成することはできません。したがって、返済取引タイプ(メンバーからシステムへのもの)から始めるのが最善です。もっと複雑なデビットシステムでは、手数料または利子のための取引タイプも作成しなければならないかもしれません。<br><br><b>ローン返済</b>のための新しい取引タイプは、以下のようにして作成されます:<br><br>
	<ul>
		<li>取引セクション、「メニュー: 口座 &gt; 口座管理」に行ってください。
		<li>メンバーがローンを受け取る際の口座タイプを選んでください。
		通常、これは「メンバー口座」になるでしょう。 この口座タイプの<img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp; 編集アイコンをクリックしてください。
		<li>次のウィンドウでは、「<a href="${pagePrefix}account_management#transaction_type_search"><u> 取引タイプ</u></a>」ウィンドウに行き、その下の「新しい取引タイプを挿入する」ボタンをクリックしてください。しかし、そうする前に、ローン返済のための適切な取引タイプが既に利用可能かどうかを確認すべきです。
		<li>続くウィンドウ内の「移転先」ドロップダウンボックスで口座タイプを選んでください。これは、通常、「デビット口座」になりますが、どのシステム口座タイプをローンのために使うかについて考えなければなりません。<br> 残りのフィールドに記入して、必要であれば、ローカルヘルプシステムを参照してください。
	</ul>
	<br><br>
	これが完了したら、<b>ローンを認可する</b>のための新しい取引タイプを作成できます:
	<br><br>	
	<ul>
		<li>再び、取引セクション、「メニュー: 口座 &gt; 口座管理」に行ってください。
		<li>「移転先」ドロップダウンボックスで前に指定した(上を見てください)口座タイプを選んでください。それは、通常、「デビット口座」です。この口座タイプの<img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp; 編集アイコンをクリックしてください。
		<li>ローン認可のための適切な取引タイプが既に利用可能かどうか確認してください。もしそうでなければ、「新しい取引タイプを挿入する」ボタンをクリックすることにより作成してください。
		<li>続くウィンドウ内の「移転先」ドロップダウンボックスで口座タイプを選んでください。これは、通常、返済取引タイプを作成する際に以前使用した「メンバー口座」になるでしょう。移転タイプを選ぶことにより、このウィンドウの一番下にある「ローンである」チェックボックスが見えるようになります。このチェックボックスにチェックすると、いくつかの他のフィールドが見えるようになります。さらなる詳細については、<a href="#make_loan_type"><u>ここをクリック</u></a>するか、ローカルヘルプを参照してください。
	</ul>
	<br><br>
	<li><b>適切な権限を設定する:</b> ローンのための適切な<a href="${pagePrefix}groups#manage_groups"><u>権限</u></a>が設定されるようにしてください。管理者と恐らく仲介人は、ローンを認可するための<a href="${pagePrefix}groups#manage_group_permissions_admin_member"><u>権限を持たなければなりません</u></a>。ローンを閲覧および返済するため(とその他)の<a href="${pagePrefix}groups#manage_group_permissions_member"><u>メンバー権限</u></a>も設定したいかもしれません。ローンを(管理者またはメンバーによって)返済するための権限が、明示的に設定されなければならないことに留意してください。<br> また、<a href="${pagePrefix}loan_groups"><u>ローングループ</u></a>を使いたければ、それらのための権限も設定したいかもしれません。<br>
	<br>
	<li><b>ローンを認可する:</b> ローンは、メンバーの<a href="${pagePrefix}profiles"><u>プロフィール</u></a>に行き、「ローンを認可する」をクリックすることによって、許可されます。<br>
	<br><br>
	<li><b>ローンを管理する:</b> ローンは、「メニュー: 口座 &gt; ローンの管理」セクションに行くことにより、管理できます。支払いは、「メニュー: 口座 &gt; ローン支払い」から管理できます。
</ol>
<span class="member">
<i>見つけ方</i><br>
メイン「メニュー: 口座 &gt; ローン」から、あなたのローンにアクセスできます。ここでは概観を得ることができ、それは返済のための出発点です。</span> <span class="broker"><a href="${pagePrefix}profiles"><u>メンバーのプロフィール</u></a>から、あなたのメンバーのローンにアクセスできます。アクションの下には、ローン管理のための特別なセクションがあります。</span>
<hr>


<span class="admin"> <a name="make_loan_type"></a>
<h3>ローン取引タイプを作成する</h3>
(<i>ヒント: ヘルプの他のセクションへのリンクが相当数あるかもしれません。ブラウザが戻るボタンを表示しない場合、バックスペースボタンを使って戻ってください。)</i> <br><br> 「ローンである」チェックボックスにチェックすると、それは作成中の<a href="${pagePrefix}account_management#transaction_types"><u>取引タイプ</u></a>が<a href="#top"><u>ローン</u></a>であることを意味します。このチェックボックスにチェックすると、ローン設定が下に現れます。<br> ローンの場合、いくつかの他のフィールドも指定されなければなりません。それらのフィールドのうち最も重要なものは、一番最初の「ローンタイプ」フィールドです。まずこのタイプを選んでください。その選択が、他のどのフィールドが見えるかを決定します。<br> ちょうど今、編集または作成している実際の取引タイプが、メンバー口座上にローンとしてユニットを記録するための取引タイプです。もちろん、これらの額の返済に対応する取引タイプが必要です。この対応する取引タイプは、「返済取引タイプ」フィールドにおいて指定される必要があります。これは、メンバーからシステム口座への取引タイプになるでしょう。それがまだ存在しない場合、まずそれを作成しなければなりません。これが意味するのは、現在のローン取引タイプの作成を続ける前に、まず返済タイプを作成しなければならないということです。<br><br>3つの異なる<a href="#loan_types"><u>ローンタイプ</u></a>が利用可能です。残りの見えるフィールドについては、各ローンタイプの下で論じられます:
<ul>
	<li><b>単純な支払い:</b> 特定の日付(またはその前)に返済されなければなりません。
	ローンがこのタイプのものならば、以下の他のフィールドを指定しなければなりません:
	<ul>
		<li><b>返済取引タイプ: </b> 説明については、少し上を見てください。
		<li><b>標準の返済日数:</b> これは満期までの期間です。この期間の後、そのローンは、メンバーのローン概観と管理者セクションのローン管理機能の中で「満期」として表示されます。メンバーは、ローンが満期になる前に、それを返済することになっています。
	</ul>
	<br>
	<li><b>複数支払い:</b> このタイプのローンは、定期的な(毎月の)返済に分割され、各々がそれ自体の満期日を持っています。あなたは返済タイプを指定しなければなりません。
	<br>
	<br>
	<li><b>手数料付き:</b> これは、様々なタイプの手数料と定期的な返済のあるローンです。以下の手数料を設定できます:
	<ul>
		<li><b>月利:</b> これは月ごとに計算される利子(複利)です。ローンと他の費用(利子、認可手数料)の合計額は、一定数の定期的な均等額の支払い(割賦ローン)に押し延べられます。
		<li><b>認可手数料: </b> そのローンについて支払われる必要がある1回限りの手数料です。この額は、すべての定期的返済に押し延べられます(含まれます)。
		その手数料は、ローン合計額のパーセンテージか、固定額にすることができます。
		<li><b>満期手数料:</b> これは返済が期間内に(満期日前に)完了されなかった場合に支払われなければならない固定額です。
		<li><b>満期利子: </b> これは返済が期間内に完了されなかった場合に毎日賦課される利子です。
	</ul>
</ul>
<hr class="help">
</span>


<a name="loan_types"></a>
<h3>ローンタイプ</h3>
<br><br>3つの異なる<a href="#top"><u>ローン</u></a>タイプが利用可能です:
<ul>
	<li><b>単純なローン:</b> これは、そのローンが、特定の日付に返済されなければならないことを意味します。この日付に、そのローンは満期になります。 この期間内に、メンバーは、全額を一度に支払うか、数回の支払いでその額の一部を支払うかを選べます。唯一重要なのは、満期日より前にすべて返済されなければならないということです。
	<li><b>複数支払い:</b><br> このローンタイプでは、返済は定期的な(毎月の)返済に分割されます。メンバーへのローンを認可する際に、最初のローン返済日とローン<a href="#component"><u>構成要素</u></a>の数を指定できます。どのローン割賦金にも、それ独自の(毎月の)満期日があります。割賦金が満期になると、そのメンバーのローン割賦金概観と管理者セクションのローン管理機能の中で、「満期」として表示されます。
	<li><b>手数料付き:</b> これは、様々なタイプの手数料と定期的な返済のあるローンです。どの手数料にも、それ独自の返済タイプがあります。 それは前のローンタイプと同じですが、追加的に可能な手数料があります。
</ul>
<hr class="help">


<span class="admin broker"> <a NAME="loan"></a>
<h3>ローンを認可する</h3>
この機能で、メンバーに<a href="#top"><u>ローン</u></a>を与えられます。 ローンを与えられるようになるには、まず、ある条件が満たされなければなりません。それを見るには、<a href="#top"><u>ここをクリック</u></a>してください。<br><br>ローンを認可するには、以下のフィールドが記入される必要があります:
<ul>
	<li><b>ローングループ:</b> このオプションは、そのメンバーが責任のあるメンバーか、より多くの<a href="${pagePrefix}loan_groups"><u>ローングループ</u></a>の1つの一員であるかを表示します。どのローングループも関わらせたくなく、その代わりに、そのローンを特定のメンバー個人に渡したければ、「パーソナル」オプションを選択してください。 <br>
	<li><b>識別子:</b> これはローンを特定する名称です。あなたは何でも好きなものを選べます。<br> 注:識別子フィールドは、標準のデータベースに備わっている<a href="${pagePrefix}custom_fields"><u>カスタムローンフィールド</u></a>です。あなたはそれを削除するか、異なるルールをもつ他のカスタムフィールドを作成することができます。 <br>
	<li><b>ローンタイプ:</b> これは、そのフォームの中で最も重要なフィールドです。 ここでは、どの取引タイプにそのローンが属するかを選択します。これらの取引タイプの各々は、3つの可能な<a href="#loan_types"><u>ローンタイプ</u></a>のうちの1つを含意します。含意されたローンタイプに依って、そのフォームの残りの部分がフィールドを表示します。私たちは、下で<b>ローンタイプに固有のフィールド</b>を扱う予定です。<br>
	<li><b>説明:</b> そのローンのための説明を入力してください。<br>
	<li><b>額:</b> これはそのメンバーが口座上に得る合計額です。それは初期負債額またはそのローンの「元金」です。<br>
	<li><b>過去の日付で認可する:</b> このローンのための日付が今日<b>ではなく</b>、その代わりに過去における日付であるべきならば、このチェックボックスにチェックしてください。このボックスにチェックすると、現れる追加的なフィールド内でその日付を指定するよう求められます。<br>
	<li><i>ローンタイプに固有のフィールド:</i> そのフォーム内の残りのフィールドは、「ローンタイプ」ドロップダウンで何を選ぶかに依存します。
	<ul>
		<li><b>単純なローン:</b> 選んだローン取引タイプが「単純なローン」を含意する場合、以下のフィールドが見えます:
		<ul>
			<li><b>返済日:</b> そのローンが返済(「送金」)される必要がある日付。この日付に、アラートが生成され、(ローン管理セクション内で)ローン状態が「満期」に変化します。
		</ul>
		<br>
		<br>
		<li><b>複数支払いローン:</b> 選んだローン取引タイプが「複数返済ローン」タイプを含意するならば、以下のフィールドが見えます:
		<ul>
			<li><b>最初の満期日:</b> ローン返済は、いくつかの「ローン<a href="#component"><u>構成要素</u></a>」に分割されます。ここでは、最初のローン構成要素(したがって、ローン全体<b>ではない</b>)が返済される必要がある日付を入力します。この日付に、アラートが生成され、ローン構成要素状態が「満期」に変化します。
			<li><b>返済の数:</b> 毎月の返済(ローン構成要素)の数。
			<li><b>計算する:</b> 計算ボタンは、異なるローン返済とそれらの満期日を示します。それらの日付と値は変更できます。
			値を変更するならば、構成要素の合計額がローン合計額と同じであることを確認してください。
		</ul>
		<br>
		<li><b>手数料付きのローン:</b> 選んだローン取引タイプが「手数料付きローン」タイプを含意するならば、以下のフィールドが見えます:
		<ul>
			<li><b>すべての利子および手数料設定:</b> 「説明」フィールドの上のこれらのフィールドは、支払われるべき手数料を示します。それらは単に情報のためのものであり、変更できません。さらなる情報については<a href="#make_loan_type"><u>ここをクリック</u></a>してください。
			<li><b>最初の満期日:</b> ローン返済は、いくつかの「ローン<a href="#component"><u>構成要素</u></a>」に分割されます。ここでは、最初のローン構成要素(したがって、ローン全体<b>ではない</b>)が返済される必要がある日付を入力します。この日付に、アラートが生成され、ローン構成要素状態が「満期」に変化します。
			<li><b>返済の数:</b> 毎月の返済(構成要素)の数。
			<li><b>表示する:</b> このボタンは、異なるローン構成要素とそれらの満期日を示します。それらの日付と値は、直接には変更できません。返済の合計額または数の修正によってのみ、それらを変更できます。表示される額は、様々な手数料を含んでいます。
		</ul>
		<br>
	</ul>
</ul>
<br><br>注:必要であれば、<a href="${pagePrefix}custom_fields"><u>追加的なローンフィールド</u></a>を作成することが可能です。たとえば、ローン契約数です。
<hr class="help">
</span>


<span class="admin broker"> <a name="loan_confirm"></a>
<h3>ローン確認</h3>
この画面は、ローンが発行される前に、単にローン情報を確認します。
ローンを発行するには、その情報を確認して、「送信」をクリックしてください。
<hr class="help">
</span>


<span class="admin"> <a NAME="search_loans_by_admin"></a>
<h3>ローンを検索する</h3>
この機能により、すべてのメンバー<a href="#top"><u>ローン</u></a>の概観を得られます。
様々な検索オプションがあります。 いつものように、フィールドを空のままにすると、そのフィールドについて、すべての可能な値を検索することを意味します。
<ul>
	<li><b>フィルタ:</b> 最初の2つのフィルタオプションは、ローン状態の組み合わせです。「任意の未決済」ローンは、完全には返済または放棄されていないすべてのローンであり、「任意の完了」ローンは、完全に返済または放棄されているすべてのローンです。<br> 残りのフィルタオプションは、異なるローン<a href="#status"><u>状態</u></a>です。
	<li><b>カスタムフィールド:</b> カスタム支払いフィールドがローンタイプについて定められ、ローン検索内に含まれるように設定されると、それはフィルタオプションの後ろに表示されます。
	<li><b>ローンタイプ:</b> 複数のローンタイプが存在する場合、ドロップダウンボックスでそれらを選択できます。ここでは、ローンタイプは、そのローンが属する<a href="${pagePrefix}account_management#transaction_types"><u>取引タイプ</u></a>のことです。
	<li><b>メンバーログイン名/氏名:</b> このオプションにより、特定のメンバーのローンを検索できます。入力フィールドは、入力中に自動補完されます。</li>
	<li><b>仲介人(ローンエージェント)ログイン名/氏名:</b> このオプションにより、特定の<a href="${pagePrefix}brokering"><u>仲介人</u></a>(エージェント)に関連したすべてのメンバーローンを閲覧できます。入力フィールドは、入力中に自動補完されます。
	<li><b>取引番号:</b> 取引番号がシステム上で有効であれば、取引番号によって検索できます。
	<li><b>ローングループ:</b> このオプションにより、特定の<a href="${pagePrefix}loan_groups"><u>ローングループ</u></a>に与えられたローンを検索できます。このオプションは、ローングループがシステム内に存在する場合にのみ見えます。
	<li><b>認可日の期間:</b> このオプションにより、特定の期間内に認可されたローンを検索できます。
	<li><b>満期日の期間:</b> このオプションにより、特定の期間内に満期になるローンを検索できます。
	<li><b>支払日の期間:</b> このオプションにより、特定の期間内に支払われたローン構成要素を検索できます。
</ul>
「送信」をクリックして、クエリを発行してください。
<hr class="help">
</span>


<a NAME="search_loans_result"></a>
<h3>ローン検索結果</h3>
このウィンドウは、<a href="#top"><u>ローン</u></a>検索クエリの結果を示します。そのウィンドウは、以下の情報のリストを示します(移動元といくつかの設定に依っては、すべての列は見えないかもしれません):
<ul>
	<li><b>メンバー:</b> そのローンを受け取ったメンバー。氏名をクリックして、<a href="${pagePrefix}profiles"><u>プロフィール</u></a>に移動してください。
	<li><b>説明:</b> そのローンの説明。
	<li><b>額:</b> そのローンの合計額。
	<li><b>残額:</b> そのメンバーが依然として支払わなければならないローンの合計額。
	<li><b>支払い:</b> ローン<a href="#component"><u>構成要素</u></a>の数。
	最初の数字は、既になされた返済の額です。 2番目の数字(フォワード・スラッシュの後)は、ローン構成要素の総数です。これは、そのリストが割賦金のない単純なローンのみを含む場合は見えません。
	<li><b>編集アイコン(<img border="0" src="${images}/edit.gif" width="16"
		height="16">&nbsp;): </b> そのローンの詳細をいくつかの追加的な情報とともに閲覧するには、これを使ってください。
</ul>
そのウィンドウの右上には、2,3の他の利用可能なアイコンがあります。<img border="0" src="${images}/save.gif" width="16" height="16">
&nbsp; アイコンはリストを<a href="#csv"><u>CSV</u></a>ファイルにエクスポートします。<img border="0" src="${images}/print.gif" width="16" height="16"> &nbsp;印刷アイコンは、すべてのリスト上のローンの詳細が書かれた印刷ページを開きます。
<hr class="help">


<a NAME="search_loans_member_by_admin"></a>
<a NAME="search_loans_by_member"></a>
<a NAME="search_loans_member_by_broker"></a>
<span class="admin broker">
<h3>メンバーのローンを検索する</h3>
</span>
<span class="member">
<h3>自分のローンを検索する</h3>
</span>
この機能により、<span class="admin broker">メンバーの</span><a href="#top"><u>ローン</u></a>を概観できます。
「<a href="#open"><u>未決済</u></a>
」または「<a href="#closed"><u>完了</u></a>
」のラジオボタンを選んでください。下の<a href="#search_loans_result"><u>検索結果ウィンドウ</u></a>が結果を表示します。
<hr class="help">


<span class="admin"> <a NAME="search_loan_payments"></a>
<h3>ローン支払いを検索する</h3>
このページでは、<a href="#top"><u>ローン</u></a>支払い情報を検索できます。まだ支払われていないローン支払いも検索可能です。以下のフィールドが利用可能です。いつものように、どのフィールドも指定しなければ、そのフィールドについて可能なすべての値が得られます:
<ul>
	<li><b>状態:</a></b> ここでは、可能なローン<a href="#status"><u>状態</u></a>について検索できます。
	<li><b>カスタムフィールド:</b> カスタム支払いフィールドがローンタイプについて定められ、ローン検索内に含まれるように設定されると、それはフィルタオプションの後ろに表示されます。
	<li><b>取引タイプ:</b> これは、そのローンのローン<a href="${pagePrefix}account_management#transaction_types"><u> 取引タイプ</u></a>です。(複数のローン取引タイプが存在する場合にのみ表示されます。)
	<li><b>メンバーログイン名/メンバー氏名:</b> これは、借り手のログイン名と実名です。
	<li><b>仲介人ログイン名/氏名:</b> これは、借り手の<a href="${pagePrefix}brokering"><u>仲介人</u></a>のログイン名/氏名です。
	<li><b>満期日の期間:</b> このオプションにより、特定の期間内に満期になるローンを検索できます。
	<li><b>支払日の期間:</b> このオプションにより、特定の期間内に支払われたローン構成要素を検索できます。
</ul>
<hr class="help">
</span>


<span class="admin">
<a NAME="search_loan_payments_result"></a>
<h3>ローン支払い検索結果</h3>
このウィンドウはローン支払い検索クエリの結果を表示します。 
<ul>
	<li><b>メンバー:</b> そのローンを受け取ったメンバー。氏名をクリックして、<a href="${pagePrefix}profiles"><u>プロフィール</u></a>に移動してください。
	<li><b>日付:</b> ローン支払いの満期日。
	<li><b>額:</b> そのローンの合計額。
	<li><b>状態:</b> ローン返済の<a href="#status"><u>状態</u></a>。
	<li><b>返済済み:</b> 内部で返済されたローン支払いの額。
	<li><b>放棄済み:</b> 放棄されたローン支払いの額。
	<li><b>ビューアイコン<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;: </b> いくらかの追加的情報とともにローン支払い詳細を見るには、これを使ってください。
</ul>
そのウィンドウの右上には、2,3の他の利用可能なアイコンがあります。<img border="0" src="${images}/save.gif" width="16" height="16">
&nbsp; アイコンはリストを<a href="#csv"><u>CSV</u></a>ファイルにエクスポートします。<img border="0" src="${images}/print.gif" width="16" height="16"> &nbsp;印刷アイコンは、すべてのリスト上のローンの詳細が書かれた印刷ページを開きます。
<hr class="help">
</span>


<a NAME="loan_detail"></a>
<h3>ローン詳細</h3>
このページは<a href="#top"><u>ローン</u></a>についての詳細を表示します。ローンのタイプに依って、そのページはいくつかのローン値を示すでしょう。<br><br>印刷アイコン(<img border="0" src="${images}/print.gif" width="16" height="16">&nbsp;)は、ローンとすべてのローン<a href="#component"><u>構成要素</u></a>の詳細の書かれた印刷可能なページを開くでしょう。<span class="admin"> ローンのいくつかの特別な状態では(それが「満期」または「処理中」の状態にある時には)、下の「このローンを...としてマークする」ボタンをクリックすることにより、<a href="#status"><u>状態</u></a>を変更できます。</span>
<hr class="help">


<a NAME="loan_parcels_detail"></a>
<h3>ローン返済の詳細</h3>
このページは、<a href="#top"><u>ローン</u></a><a href="#component"><u>構成要素</u></a>についての詳細を示します。ローンのすべての構成要素がこの概観内にリストで示されます。 その表は、とても分かり易いものです。<a href="#status"><u>状態</u></a>は、いくつかの値のうちの1つになり得ます。
<hr class="help">


<span class="admin"> <a NAME="loan_to_members"></a>
<h3>メンバーへのローン</h3>
このページは、選択された<a href="#top"><u>ローン</u></a>の<a href="${pagePrefix}loan_groups"><u>ローングループ</u></a>に属するメンバーのリストを示します。「責任のある」メンバー(そのローンを受け取ったメンバー)の名前は、赤で表示されます。氏名をクリックすると、そのメンバーの<a href="${pagePrefix}profiles"><u>プロフィール</u></a>に移動します。
<hr class="help">
</span>


<a NAME="loan_repayment_by_admin"></a>
<a NAME="loan_repayment_by_member"></a>
<h3>ローン返済</h3>
このページは、<a href="#top"><u>ローン</u></a>構成要素と そのローン<a href="#component"><u>構成要素</u></a>の返済<span class="admin">または<a href="#discard"><u>放棄</u></a></span>の可能性についての情報を示します。<br> その額は改変できますが、依然として支払うべき額で事前に記入されています。<span class="admin"> 「過去の日付で支払う」チェックボックスにチェックを入れると、そのローン返済は過去の日付で記帳されます。追加的な編集ボックスでこの日付を指定するよう求められます。</span> <br><br>そのローンが<a href="#loan_types"><u>複数の返済</u></a>のあるローン(これは手数料付きローンも含むでしょう)ならば、いくつかの追加的なフィールドが利用可能です。これらのフィールドは、単純なローンでは利用可能でありません。 「支払い番号」は、上の概観内のローン構成要素を参照しています。たいてい、あなたは並んでいる次の支払い(利用可能な一番小さい支払い番号で、未だ返済されていないもの)を支払うでしょうが、別の構成要素を支払うことを選んでも良いでしょう。<br><br><span class="admin">そのローンを返済または放棄するボタンのいずれか1つを使ってください。</span> <span class="member">そのローン(の一部)を返済するには、「返済」ボタンをクリックしてください。</span>
<hr class="help">
<hr>

<a name="glossary"></a>
<h2>用語集</h2>
<a name="component"></a>
<b>構成要素</b> <br>負債の決済に成功した多くの支払いの1つです。ローン返済がいくつかの部分に分割されている場合、これらの部分の各々は割賦金と呼ばれます。<br><br> <a name="csv"></a> <b>CSV(ファイル)</b> <br>CSVは「カンマ区切りの値」のことです。それはCyclos内の様々な検索結果ウィンドウからダウンロードできるデータのファイルのためのフォーマットです。このフォーマットでは、フィールドの値は、タイトルが示唆するように、カンマによって区切られています(ただし、他の任意の文字も区切りとして使えます)。<br> このフォーマットは、通常、Open Office CalcやMicrosoft Excelのような表計算プログラムで開けます。あなたは、マクロとの組み合わせで、テキストエディタでCSVファイルを処理することもできます。WordやWord Perfectのようなプログラムには、入力ファイルを綺麗に編集された体裁のよいドキュメントへと自動的に処理するための素晴らしいマクロ機能があります。<br><br> <a name="status"></a> <b>ローン状態</b><br> ローン状態が、ローンまたはローン<a href="#component"><u>構成要素</u></a>に当てはまります。それは以下のものになり得ます:
<ul>
	<li><b>未決済:</b> そのローンは未決済です。つまり、返済が完了していないが、満期日に達していないことを意味します。そのメンバーには、依然として返済の義務があります。
	<li><b>満期:</b> ローン返済日が過ぎたものの、その返済が完了していません。
	<li><b>完了/返済済み:</b> そのローンは、返済済みであるか放棄済みであり、管理者により完了されています。そのメンバーには、もはや返済義務がありません。
	<li><b>放棄済み:</b> ローンが他の手段、たとえば財または伝統的貨幣によって返済済みならば、ローン構成要素は通常放棄されます。放棄済みのローン構成要素は、完了したものと見なせます。<br>
	<li><b>処理中:</b> ローンが満期日に達した場合、管理者はその状態を「処理中」に変更できます。たいていは、ローンの再交渉のためです。この状態の後、管理者は、そのローンを「回収済み」状態か「回収不可能」状態のいずれかに移します(次を見てください)。この状態には、満期ローンからのみ到達できます。それは、そのローンは満期ですが、当事者がそれをどうするかに関して交渉し続けていることを意味します。 <br>
	<li><b>回収済み:</b> これは「処理中」状態後の「終了」状態です。それは、そのローンが回収済みであることを意味します。
	<li><b>回収不可能:</b> この状態は「処理中」状態からのみ到達できます。厳密に見ると、それはこのローンが依然として支払われるべきですが、すべての当事者がそのメンバーには支払い可能でないと見なしており、もはやいかなる支払いも期待していないことを意味します。そのローンは、ある種の「凍結された」状態にあります。
	<li><b>認可保留中:</b> そのローンの支払いは、認可される必要があります。一度、そのローン支払いが認可されると、その移転は自動的に行われます。(このオプションは、管理者が認可された支払いを見る権限を持っている場合にのみ、検索フィルタ内に現れます。)
	<li><b>認可拒否済み:</b> そのローンの支払いは拒否されています。これは、そのローンが管理者によりキャンセル済みであることを意味します。 (このオプションは、管理者が認可された支払いを見る権限を持っている場合にのみ、検索フィルタ内に現れます。)
</ul>

</div> <%--  page-break end --%>
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