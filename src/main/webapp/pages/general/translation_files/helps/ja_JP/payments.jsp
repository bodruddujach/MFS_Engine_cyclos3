<div style="page-break-after: always;">
<br><br>
メンバーは、別のメンバーまたはシステム口座(コミュニティ、組織など)に対して支払いを行えます。また、各メンバーが同一通貨で複数の口座を持っている場合には、メンバー自身の口座間でユニットを移転できます。支払いは、将来の日付でスケジュールすることもできます。 すべての支払いには、取引領収書を印刷するための印刷ボタンがあります。<span class="admin">支払いは、特定の状況下で元に戻す(<a href="#charge_back"><u>チャージバックする</u></a>)こともできます。</span> <br><br><i>見つけ方</i><br> <span class="member">メインWebアクセスページ内での支払いは、3つの場所から始められます。メニューから:</b> これには2つのバージョンがあります:
<ul>
	<li><b>メンバーへの支払い:</b> 「メニュー: 口座 &gt; メンバー支払い」
	<li><b>システムへの支払い:</b> 「メニュー: 口座 &gt; システム支払い」
	<li><b>プロフィールから:</b> 他のメンバーへの支払いは、そのメンバーの<a href="${pagePrefix}profiles"><u>プロフィール</u></a>ページからも始められます。
</ul>
</span> 
<span class="broker">仲介人は、メンバーの
<a href="${pagePrefix}profiles"><u>プロフィール</u></a>から、メンバーのための支払いを始められます。これは、他のメンバーへの支払いとシステム口座への支払いを含みます。<br><br>仲介人は、メンバーの支払いを<a href="#authorized"><u>認可する</u></a>こともできます。これには「メニュー: 仲介 &gt; 認可待ち」と「メニュー: 仲介 &gt; 認可履歴」から行けます。<br>
</span> <span class="admin">支払いは、様々な場所から始められます。
言及されるオプションの利用可能性は、もちろん、あなたの組織の設定と様々なグループの権限に依存します。
<ul>
	<li><b>プロフィール:</b> メンバーの<a href="${pagePrefix}profiles"><u>プロフィール</u></a>から、システムへの支払いと同様に、他のメンバーへの支払いを行えます。
	<li><b>システムからシステムへの支払い:</b> 「メニュー: 口座 &gt; システム支払い」から始められます。これらは1つのシステム口座から別のシステム口座への移転です。
	<li><b>システムからメンバーへの支払い:</b> 「メニュー: 口座 &gt; メンバー支払い」から始められます。これらはシステム口座からメンバーへの支払いです。
</ul>
また、様々なタイプの特別な支払いがあり、たいていはメインメニューから行けます:
<ul>
	<li><b><a href="#authorized"><u>認可</u></a>:</b> 「メニュー: 口座 &gt; 認可する」と「メニュー: 口座 &gt; 認可履歴」から行けます。
	<li><b><a href="#scheduled"><u>スケジュールされた支払い</u></a>:</b> 「メニュー: 口座 &gt; スケジュールされた支払い」から行けます。
	<li><b>ローン返済:</b> 「メニュー: 口座 &gt; ローン支払い」から行けます。この主題は、<a href="${pagePrefix}loans"><u>ローンヘルプファイル</u></a>内で扱われています。
</ul>
<br>
</span> 支払いを直接行うことに加えて、<a href="${pagePrefix}invoices"><u>請求書</u></a>への返答によっても支払いできることに留意してください。<span class="admin"> <br><br><i>動かし方</i><br> 最も重要な問題は、各支払いのために、支払いタイプが存在しなければならないということです。特定の支払いのための支払いタイプを定義していなければ、その支払いは行えません。それらが支払われる元の口座を管理することによって、取引タイプを管理できます。 そうするには、「メニュー: 口座 &gt; 口座の管理」に行き、支払人の口座タイプを選択してください。そこには、この口座のためのすべての利用可能な取引タイプの<a href="${pagePrefix}account_management#transaction_type_search"><u>概観</u></a>が得られ、(権限があれば)新しいタイプを追加することもできます。<br> 支払いタイプが存在しても、様々なグループのためにそれを使うための権限を設定する必要があります。 
<ul>
	<li>管理者は、システム支払いを行うための<a href="${pagePrefix}groups#manage_group_permissions_admin_system"><u>権限</u></a>を持てます。「システム支払い」ブロックは、様々な設定を含みます。
	<li>管理者は、メンバーのための支払いを行うための<a href="${pagePrefix}groups#manage_group_permissions_admin_member"><u>権限</u></a>も持てます。「メンバー支払い」ブロックは、様々な設定を含みます。
	<li>メンバーのために、支払いを行うための<a href="${pagePrefix}groups#manage_group_permissions_member"><u>権限</u></a>も設定する必要があります。これは「支払い」ブロックで、様々な設定とともに行われます。そのようなブロックは、仲介人についてもあります。
	<li>仲介人は、「メンバー支払い」ブロックの下で、メンバーのために支払いを行うための<a href="${pagePrefix}groups#manage_group_permissions_broker"><u>権限</u></a>を持てます。
	<li>認可された支払いとスケジュールされた支払いについては、「口座」ブロック下に各メイングループ(メンバー、仲介人、管理者)のための設定があり、それにより、このグループは認可された支払いおよび/またはスケジュールされた支払いを見られます。
</ul>
</span>
<hr>


<a name="payments"></a>
<br><br>
<h3>支払いをする</h3>
Cyclos内で支払いをするためのフォームには、しばしばいくつかの共通の要素があります。 この短いイントロダクションでは、支払いウィンドウ内に現れるかもしれない共通の要素を扱います。たいていの場合、額と説明を記入し、それから「送信」をクリックするだけです。他の様々な場合には、いくつかの他のフィールドも記入しなければならないでしょう。<br> すべてのフィールドとオプションを正しい順序で、つまり、上から下へ選ぶことが重要であることに留意してください。たとえば、メンバー名を要求するフィールドに入力する前に、その後ろで通貨を選ぼうとしても機能しません。
<br><br>
<ul>
	<li><b>名前(ログイン名):</b> その支払いが別のメンバーに対してのものであり、なおかつ、これが文脈から既に明らかでない場合は、支払いをしたいメンバーのログイン名または実名のいずれかを記入しなければなりません。そのフィールドは自動補完によって機能します。つまり、たいていの場合、最初の数文字を入力するだけで十分です。
	<li><b>額:</b> 額を入力してください。
	<li><b>通貨:</b> このフィールドは、金額フィールドのすぐ後に現れます。 それは複数の通貨が可能である場合にのみ見えます。これはあなたの組織のローカル設定に依存します。
	<li><b>取引タイプ:</b> 複数の取引タイプが可能であるということもあり得ます。その場合、ドロップダウンボックスから取引タイプを選ばなければなりません。
	<li class="admin"><b>過去における支払い:</b> 管理者は、過去における支払いを行うことを選択できます。これは、たいていアカウンタビリティの問題のために行われるもので、稀な場合にのみ使われるべきです。これは、<a href="${pagePrefix}groups#manage_group_permissions_admin_member"><u> 管理者に対するメンバー権限</u></a>において有効化されなければなりません。
	<li><b>スケジューリング:</b> スケジュールされた支払いが、このタイプの支払いについて許可されている場合、その支払いを将来の日付で(自動的に)処理させるか、繰り返し起こる将来の日付における複数の支払いとして処理させることができます(さらなる情報については、<a href="#pay_scheduled"><u>スケジュールされた支払い</u></a>を見てください)。
</ul>
<br><br>送信後、あなたは確認を求められます。 ユニットが、あなたの口座から他の関係者の口座へと直接移転されます。その取引は、送信者(支払人)のための口座履歴(赤字で-記号付き)と、受取人のための口座履歴(青地で+記号付き)の両方に現れます。
<hr class="help">


<a name="transaction_confirm"></a>
<h3>取引確認</h3>
このウィンドウでは、あなたが今入力した支払いを確認するよう求められます。 すべての情報を徹底的に確認し、すべて正しければ「送信」をクリックしてください。<br> 誤りがあった場合は、「戻る」ボタンを使えます。
<hr class="help">


<A NAME="to_member"></A>
<h3>メンバーへの支払い</h3>
このウィンドウにより、メンバーへの支払いを行えます。 たいていの場合、説明、額、そして、まだそれが知られていない場合、メンバー名を記入し、「送信」をクリックするだけです。<br> 支払いウィンドウの一般的な説明については、<a href="#payments"><u>ここをクリック</u></a>してください。
<hr class="help">


<A NAME="to_system"></A>
<h3 class="admin">システム口座間の移転</h3>
<h3 class="member">システムへの支払い</h3>
<span class="admin">このウィンドウでは、システム口座間の支払いを行えます。</span> <span class="member">このウィンドウでは、組織口座または「システム口座」の1つに対して、支払いを行えます。</span> 支払いウィンドウの一般的な説明については、 <a href="#payments"><u>ここをクリック</u></a>してください。
<hr class="help">


<A NAME="as_member_to_system"></A>
<span class="broker admin">
<h3>システムへの支払い</h3>
このウィンドウでは、メンバーとしてメンバー口座からシステム口座へ支払いを行えます。<br><br> 支払いウィンドウの一般的な説明については、<a href="#payments"><u>ここをクリック</u></a>してください。
<hr class="help">
</span>


<span class="broker admin"> <A NAME="as_member_to_member"></A>
<h3>メンバーとしてのメンバーへの支払い</h3>
このウィンドウでは、メンバーとして、メンバー口座から別のメンバー口座へ支払いを行えます。<br><br>支払いウィンドウの一般的な説明については、<a href="#payments"><u>ここをクリック</u></a>してください。
<hr class="help">
</span>


<A NAME="member_self_payments"></A>
<h3 class='member'>自分の口座間の移転</h3>
<h3 class='admin'>メンバーの口座間の移転(管理者による)</h3>
<span class="admin">管理者(権限のある)が、あたかもそのメンバーであるかのように、自己支払いを行うことが可能です。</span> 自己支払いにより、同じ所有者(メンバー)の1つの口座から別の口座への移転が可能です。自己支払いは、別のメンバーへの一般的な支払いと同様に機能します。<br><br> 支払いウィンドウの一般的な説明については、<a href="#payments"><u>ここをクリック</u></a>してください。
<hr>


<a name="scheduled"></a>
<h2>スケジュールされた支払い</h2>
スケジュールされた支払い機能により、メンバーは、 他の口座への将来のスケジュールされた支払い(割賦金)を作成できます。これは、一度だけ行われる単一のスケジュールされた支払い、「パック」または繰り返し起こる(定期的な)支払い(たとえば、毎月)のような複数の支払いにすることができます。その支払いは、指定された日付で自動的に行われます。<br> スケジュールされた支払いは、請求書と組み合わせることもできます。別のメンバーに請求書を送信するメンバーは、その請求書が直接支払われる必要があるか、あるいは、スケジュールされた(将来の)日付または複数の支払い日に支払うことができるかを(もし権限があれば)指定できます。一度、受け取るメンバーがその請求書を承諾すると、 彼のスケジュールされた支払いリスト内に、スケジュールされた支払いが現れます(そして、請求書を送信したメンバーによって指定された日付で賦課されます)。<span class="admin">(システム設定内で)任意のタイプの支払いがスケジュールされるようにすることも可能です。スケジュールされた支払いを可能にするには、以下のことを行う必要があります:
<ol>
	<li><b>権限:</b> まず、メンバーグループのためにすべての<a href="${pagePrefix}groups#manage_group_permissions_member"><u>権限</u></a>を設定する必要があります。執筆時点では、メンバーグループのために有効化できる権限は3つありますが、あなたはそれらをすべて有効化したくはないかもしれません。<a href="${pagePrefix}brokering"><u>仲介人</u></a>または管理者が、そのメンバーのためのスケジュールされた支払いを行えるようにしたい場合は、それらのグループに対する権限も確認してください。
	<li><b>グループ設定:</b> メンバーグループについては、スケジュールされた支払いのための<a
href="${pagePrefix}groups#edit_member_group"><u>特別なグループ設定</u></a>があります
	(「メニュー: ユーザーとグループ &gt; 権限グループ」で、メンバーグループの編集アイコン<img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp;をクリックしてください)。これはしばしば見落とされるので、忘れずに設定してください。
	<li><b>取引タイプ:</b> <a href="${pagePrefix}account_management#transaction_types"><u> 取引タイプ</u></a>内で、スケジューリングが有効化されなければなりません。<a href="${pagePrefix}account_management#transaction_type_details"><u>取引タイプ特性ウィンドウ</u></a>内に、「スケジュールされた支払いを許可する」という特別なチェックボックスがあります。<br> <b>注:</b> 一部の支払いタイプについては、スケジューリングは利用可能ではありません。これらは、メンバーからシステムへの取引タイプ、そして、自己支払い取引タイプです。
</ol>
これにより、スケジュールされた支払いが機能するように設定されるはずです。 この場合、各々の<a href="#payments"><u>支払いウィンドウ</u></a>は、これが関係する時に、「スケジューリング」ドロップダウンを表示します。<br><br>スケジュールされた支払いは、「メニュー: 口座 &gt; スケジュールされた支払い」から検索できます。</span>
<span class="member">あなたのスケジュールされた支払いは、「メニュー: 口座 &gt; スケジュールされた支払い」から検索できます。</span>
<hr class="help">


<a name="pay_scheduled"></a>
<h3>スケジュールされた支払いを行う</h3>
このヘルプ項目は、<a href="#scheduled"><u>スケジュールされた支払い</u></a>についてであり、支払いウィンドウの内部の、このための特別なフィールドを説明します。<br><br>「スケジューリング」ドロップダウンには、3つの可能な値があります:
<ul>
	<li><b>スケジュールされない(直ちに処理する):</b> スケジュールされた支払いを使いたくない場合は、これを選んでください。
	<li><b>将来の日付でスケジュールする:</b> このオプションを選ぶと、あなたが指定する日付でその支払いが行われます。その日付は、「～にスケジュール」編集ボックス内で指定されなければなりません。それはあなたがこのオプションを選択すると現れるはずです。カレンダー<img border="0" src="${images}/calendar.gif" width="16" height="16">&nbsp;アイコンから、日付選択機能を選びたいかもしれません。
	<li><b>複数のスケジュールされた支払い:</b> これは最も洗練された形のスケジュールされた支払いです。このオプションを選ぶことにより、単一の支払いを複数の部分的な支払いに分割できます。各部分支払いについて、日付と額を個々に設定できます。<br> そのフォームでは以下の要素が利用可能です:
	<ul>
		<li><b>支払い回数:</b> あなたが行いたい部分支払いの数。
		例: 1週間ごとに10回の支払い。上で指定した額が、均等額に分割されます。
		<li><b>最初の支払日:</b> <img border="0" src="${images}/calendar.gif" width="16" height="16">&nbsp; カレンダーアイコンから、日付選択機能を使いたいかもしれません。
		<li><b>～毎に支払い:</b> これら2つのドロップダウンを使って、期間を選んでください。
		<li><b>計算する:</b> どの日付に正確にいくら支払われるかを見るために、このボタンを使いたいかもしれません。このボタンをクリックすると、支払日と支払額の概観が示されます。それらの合計が上で記入した額になるように気を付ける限り、これらの日付と額を変更できます。<br>
		注: このオプションは何も処理しません。それは額と日付をプレビューするために使われるだけです。
	</ul>
</ul>
<hr class="help">


<A NAME="scheduled_payments_by_admin"></a>

<A NAME="scheduled_payments_by_member"></a>
<h3>スケジュールされた支払いを検索する</h3>
ここでは、<a href="#scheduled"><u>スケジュールされた支払い</u></a>を検索できます。フォーム内の以下の要素を指定できます。 フィールドを空のままにすると、そのフィールドについてすべての値を返すということに留意してください。
<ul>
	<li><b>検索タイプ:</b> ここでは、「出て行った」支払いか「入ってきた」支払いを指定できます。出て行った支払いは、単に通常の支払いです。「入ってきた」は、<a href="${pagePrefix}invoices"><u>請求書</u></a>を意味し、スケジュールされた支払いで支払えるように送信者が指定したものです。
	<li><b>口座:</b> ドロップダウンで口座タイプを選択してください。 これは、複数の口座が利用可能な場合にのみ見えます。
	<li><b>状態:</b> 「未決済」は、「まだ支払われていない」ことを意味します。残りのものは一目瞭然です。
	<li><b>ログイン名/メンバー名:</b> これら2つのフィールド内で、その支払いが行われた先のメンバーを指定できます。フィールドは自動補完されます。
	<li><b>開始日/終了日:</b> ここで期間を指定しても良いでしょう。 カレンダー<img border="0" src="${images}/calendar.gif" width="16" height="16">&nbsp;アイコンから、日付選択機能を使いたいかもしれません。
</ul>
<hr class="help">


<a name="view_scheduled_payment"></a>
<h3>スケジュールされた支払いの詳細</h3>
このウィンドウは、<a href="#scheduled"><u>スケジュールされた支払い</u></a>の詳細を表示します。人物の名前をクリックして、彼らのプロフィールに行けます。
<br> 印刷<img border="0" src="${images}/print.gif" width="16" height="16">&nbsp;アイコンをクリックして、その詳細を印刷できます。<br><br> あなたに権限があれば、そのウィンドウの一番下に2つのボタンがあるかもしれません:
<ul>
	<li><b>ブロックする:</b> これにより、その支払いを一時的にブロックできます。 それは、あなたがキャンセルまたはブロック解除するまで実行されません。ブロックされた支払いは、ブロック解除できます。
	<li><b>ブロック解除する:</b> 元々スケジュールされた日付に行われるように、その支払いのブロックを解除します。その支払日が過ぎている場合には、このボタンは見えるようになりません。その場合、下の<a href="#sheduled_payment_transfers">スケジュールされた支払い移転</a>ウィンドウに行き、 その移転のビュー<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;アイコンをクリックすることにより、支払いを行えます。
	<li><b>キャンセルする:</b> ブロックボタンとの違いは、スケジュールされた支払いのキャンセルは決定的だということです。未決済であるスケジュールされた支払いは支払われずに決定的に削除されてしまい、そのキャンセルを元に戻すオプションはありません。既に支払われているスケジュールされた支払いは、元に戻せません。
</ul>
<hr class="help">


<br><br><A NAME="sheduled_payment_transfers"></A>
<h3>スケジュールされた支払い移転</h3>
このページは、<a href="#pay_scheduled"><u>複数のスケジュールされた支払い</u></a>の一部であるすべての移転(部分支払い)を表示します。ビュー<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;アイコンをクリックして、支払いの詳細に行けます。
<hr class="help">


<A NAME="scheduled_payments_result"></A>
<h3>検索結果(スケジュールされた支払い)</h3>
この結果ウィンドウは、上で指定した検索基準に従って、<a href="#scheduled"><u>スケジュールされた支払い</u></a>のリストを表示します。<br><br>以下のものが表示されます:
<ul>
	<li><b>日付:</b> スケジューリングの日付。
	<li><b>ログイン名:</b> これをクリックして、そのメンバーのプロフィールに行けます。
	<li><b>額:</b>
	<li><b>部分:</b> 最初の数字は、このスケジュールされた支払いのいくつの部分的支払いが既に支払われたかを示し、2番目の数字は、このスケジュールされた支払い内の部分的支払いの総数を示します。その支払いが複数の部分的支払いに分割されておらず、1回だけの支払いである場合は、この2番目の数字は「1」になります。
	<li><b>状態:</b> 「スケジュール済み」、「ブロック済み」、「認可待ち」になり得ます(<a href="#authorized"><u>認可された支払い</u></a>を見てください)。
	<li><img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;この移転の詳細を見るには、このアイコンを使ってください。そこでは、(あなたにそのための正しい権限があれば)詳細の印刷、その支払いのブロック、ブロック解除、支払いを行えます。
</ul>
<hr>


<a name="authorized"></a>
<h2>認可された支払い</h2>
Cyclosは、支払額が実際に受取人の口座に移転される前に、まず認可を必要とするように設定できます。その認可は、設定に応じて、管理者、仲介人、または、受け取るメンバーによって行えます。複数の認可レベルが存在し得ます。つまり、上の人物のうちの複数の人が認可する必要があります。各認可レベルについて、別の基準を設定できます。<br> その支払いがまだ認可されていない限り、それは「認可待ち」状態に留まります。認可者は通知を受け、その支払いを認可(アクティブ化)することができます。メンバーと認可者の両方が、認可を必要とする保留中の支払いのリストにアクセスできます。その支払いが行われた(認可された)時には、支払人と被支払人の両方が通知を受け取ります。<span class="admin">認可された支払いは、<a href="${pagePrefix}account_management#transaction_types"><u> 取引タイプ</u></a>ごとに管理されています。様々な設定が利用可能です。<br><br>認可された支払いは、以下のように有効化できます:
<ol>
	<li><b>権限:</b> まず、すべての関連する<a href="${pagePrefix}groups#permissions"><u>権限</u></a>が設定されているように注意してください。あなたは、管理者、仲介人、メンバーのための権限を設定したいかもしれません。これらのユーザーグループの各々のために、認可に関する様々な権限があります。そのページ上でそれらを探すために、あなたのブラウザの検索機能(たいていはCTRL-F)を使いたいかもしれません。
	<li><b>取引タイプに関して認可を設定する:</b> <a href="${pagePrefix}account_management#transaction_types"><u>取引タイプ</u></a>に関して、認可を有効化する必要があります。これは、<a href="${pagePrefix}account_management#transaction_type_details"><u>取引タイプ詳細ウィンドウ</u></a>内で「認可を必要とする」フィールドから行われます。<b>注:</b> この認可オプションは、認可待ちの取引がある限り、解除できません。
	<li><b>認可レベルを設定する:</b> その後、認可レベルを設定する必要があります。これは、1つ下のウィンドウ、<a href="${pagePrefix}account_management#authorized_payment_levels"><u>認可された支払いレベルウィンドウ</u></a>内で行われます。詳細については、ローカルヘルプを参照してください。
</ol>
<span class="member">
メンバーのための認可は、その取引が行われる前に、受取人が他者からの支払いを承諾しなければならないということを意味します。この場合、受取人はその支払いを拒否することができます(たとえば、商品がない場合)。受取人と支払人の両方が、通知を受け取ります。 この設定は、請求書の使用に似ており、非常に稀です。同じシステム内で受取人認可と請求書を使わない方が良いでしょう。</span> ソフトウェア内の以下の場所で認可された支払いを探せます:
<ul>
	<li><b>メニュー: 口座 &gt; 認可する</b> あなたによって認可される必要がある支払いを概観できます。このメニューは、あなたに入ってきた支払いを認可する権限がある場合にのみ利用可能です。
	<li><b>メニュー: 口座 &gt; 認可</b> 過去または現在の、認可、拒否、キャンセルされた認可を検索できます。あなたによって行われたすべての過去の認可アクションは、このウィンドウから見つけられます。<br> このオプションは、認可アクションの検索のためのものであることに留意してください。したがって、認可待ちの移転は見つかりません(それらに対しては、まだいかなるアクションもとられていないからです)。
	<li class="broker"><b> メニュー: 仲介 &gt; 認可する</b> これは、あなたのメンバーの支払いのうち、あなたが仲介人として認可する必要があるものの概観です(あなたの個人的な認可された支払いを検索する メニュー: 口座 &gt; 認可する とは異なります)。

	<li class="broker"><b> メニュー: 仲介 &gt; 認可</b> メニュー: 口座 &gt; 認可 の項目と同じですが、あなたの仲介活動に関する認可についてではありません。
</ul>
<hr class="help">


<a name="transfers_awaiting_authorization_by_member"></a>

<a name="transfers_awaiting_authorization_by_admin"></a>
<h3>認可すべき移転</h3>
あなたによって<a href="#authorized"><u認可され</u></a>なければならない移転の概観を得るには、このウィンドウを使ってください。いつものように、フィールドを空のままにすると、そのフィールドについてすべての可能な値が検索結果に含まれます。したがって、どのフィールドも指定せずに「検索」ボタンを押すと、その結果は認可待ちのすべての支払いになります。<br><br>以下の検索基準を指定できます:
<ul>
	<li><b>ログイン名/メンバー名:</b> これらのフィールドは自動補完されますので、最初の何文字かを入力するだけで十分でしょう。
	<li><b>開始日/終了日:</b> ここで期間を指定できます。カレンダー<img border="0" src="${images}/calendar.gif" width="16" height="16">&nbsp; アイコンから、日付選択機能を使いたいかも知れません。
	<li><b>取引タイプ:</b> 取引タイプによって検索します。
	<li span class="admin"><b>仲介人が認可できない場合のみ:</b> このチェックボックスをチェックすると、管理者であるあなたしか認可できないもののみが結果に含まれます。
</ul>
結果は下のウィンドウ内に表示されます。
<hr class="help">


<a name="transfers_awaiting_authorization_result"></a>
<h3>検索結果(認可待ちの移転)</h3>
この概観では、依然として<a href="#authorized"><u>認可</u></a>待ちの取引が見えます。マイナスの額は、認可を必要とする出て行った支払いで、プラスの支払いは、認可待ちの入ってきた支払いです。<br><br>ビュー<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;アイコンをクリックして、詳細ウィンドウを開いてください。そこでは支払いを認可または拒否できます。
<hr class="help">


<a name="transfer_authorizations_by_admin"></a>

<a name="transfer_authorizations_by_member"></a>
<h3>認可された取引アクション</h3>
このウィンドウでは、<a href="#authorized"><u>認可</u></a>アクションを検索できます。そのフォームは単純明快です。 フィールドを空のままにすると、結果内にそのフィールドについてあらゆる可能な値が返されます。<br> 以下の検索オプションが利用可能です:
<ul>
	<li><b>取引タイプ:</b> 取引タイプによって検索します。
	<li><b>アクションによって検索する:</b>
	<ul>
		<li><b>認可済み:</b> 承認された支払い。
		<li><b>拒否済み:</b> 拒否された支払い。
		<li><b>キャンセル済み:</b> (他者によって)キャンセルされた支払い。
	</ul>
	<li><b>メンバーによって検索する:</b> 個々のメンバーを検索します。
	<li><b>期間によって検索する:</b> 日付範囲によって検索します。
</ul>
完了したら、そのページの一番下の「検索」ボタンをクリックできます。結果は、下のウィンドウ内に現れます。
<hr class="help">


<a name="transfers_authorizations_result"></a>
<h3>検索結果 認可履歴</h3>
上のウィンドウ内で指定した基準での検索結果を表示します。 その項目に関する詳細を得るには、<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp; ビューアイコンを使ってください。
<hr class="help">


<a name="transaction_authorizations_detail"></a>
<h3>認可アクション</h3>
このウィンドウは、上の取引に関して既に行われているすべての<a href="#authorized"><u>認可</u></a>アクションを表示します。これらは、認可だけでなく、拒否やキャンセルにもなり得ます。それは、そのアクションの日付と、誰がそのアクションを行ったのかを表示します。
<hr>

<h2>様々な支払いウィンドウ</h2>
以下は、支払いに関するいくつかの様々なヘルプウィンドウです。

<a name="accessing_channels"></a>
<h3>支払いチャネルにアクセスする</h3>
設定によっては、メンバーは様々な支払いチャネルから支払いを行えます。
<ul>
	<li>最も一般的なのは、メインWebアクセスページ(www.domain.com/cyclos)です。
	<li>もう1つの便利な支払いチャネルは、メンバーがログインとクイック支払いだけを行える単純なページ(www.domain.com/posweb/pay)です。
	<li>顧客/依頼人からの支払いをPOS(point of sale)のような方法で受け取りたいメンバー/事業者は、PosWebページ(www.domain.com/posweb/receive)を使えます。依頼人は、その支払いを認証するための個人的なPINを生成する必要があることに留意してください。
	<li>同じページで支払いと受け取りができるようにしたいメンバー/事業者は、そのアクセスチャネル(www.domain.com/posweb)を使えます。<br> このアクセスページは、一般的に、依頼人がバウチャー(スクリップ)または物理的貨幣を回収または償還できるローカルの「キャッシュポイント」によって使われます。
	<li>支払いをし、顧客/依頼人からの支払いを承諾することができる「ペイデスク」オペレータを有するメンバー/事業者については、そのオペレータがPosWebページを使えます(www.domain.com/posweb/operator)。<br>
	<li>モバイル支払いは、URL www.domain.com/cyclos/mobile (wap2) と www.domain.com/cyclos/wap (wap1) から行えます。
</ul>
支払いチャネルの利用可能性は、システムの設定に依存します。一般に、支払いの受け取りは、依頼人にPIN番号を入力するよう要求します。PosWebチャネルからの支払いの実行は、メインwebアクセスと同じように機能し、設定に依っては、ログインパスワードまたは取引パスワードを必要とします。

<hr class="help">


<a name="request"></a>
<h3>他のチャネルから支払いをリクエストする</h3>
このウィンドウでは、別の支払い手段(<a href="#accessing_channels"><u> チャネル</u></a)から支払いをリクエストできます。それは、その支払いが承諾されるとすぐにアクティブ化されるという点で、請求書によく似た機能です。違いは、この支払いリクエストは、支払いリクエストID(SMS内で与えられる)とともに、PINと確認を必要とするという点です。現在、SMS支払いリクエストのみが利用可能です。管理者は、この機能が使えるようになる前に、SMSチャネルを有効化する必要があります。<br><br>それはこのように機能します: (自動補完の)ログイン名/氏名フィールド内にメンバーを記入し、額と説明を書いて「送信」を押すと、SMSチャネルから支払いリクエストが送信されます。つまり、そのメンバーはSMSを即座に受け取り、それに返答してPINを提供することにより確認できます。支払いリクエストが送信された後、状態が変化します(下のヘルプを見てください)。そのメンバーがSMSで返答することによりその支払いを確認すると、その状態は「支払い済み」に変化します(そして、商品を手渡すか送ることができます)。
<hr class="help">


<a name="search_requests"></a>
<h3>支払いリクエストを検索する</h3>
このウィンドウでは、支払いリクエストを検索できます。 標準では、それはすべての保留中と成功した(確認済みと支払い済みの)リクエストを表示します。他の状態に関してフィルタを選んだり、メンバーで検索することもできます。<br><br>結果ウィンドウは、検索フィルタに従って支払いリクエストを表示します。そのページは5秒ごとに自動的に更新されます(そして、状態が自動的に変化します)。
<hr class="help">


<a name="account_overview"></a>
<h3>口座概観</h3>
このウィンドウは、あなたがアクセスできるすべての口座のリストを表示します。
<ul>
	<li><img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;その口座に入りたければ、このアイコンをクリックしてください。
</ul>
<hr class="help">


<a name="transaction_history"></a>
<h3>取引概要</h3>
このウィンドウには、取引を検索するための様々なオプションがあります。 以下のオプションから選択できます:
<UL>
	<LI><b>状態:</b> これは、認可を必要とする支払いの状態です。それは、メンバー支払いまたはローンについて認可が有効化されている場合にのみ現れます。それは状態を表示します:
	<ul>
		<LI><b>認可待ち:</b> その支払いまたはローンは、その移転が処理される前に認可される必要があります。
		<LI><b>処理済み:</b> その支払いまたはローンは、認可および処理されました。
		<LI><b>拒否済み:</b> その支払いまたはローンは、拒否されました。
	</ul>
	<LI><b>支払いタイプ:</b> このドロップダウンボックスにより、特定の支払いタイプに関してフィルタできます。
	<LI><b>ログイン名/メンバー氏名:</b> その人物のログイン名またはメンバー氏名を記入することにより、特定の人物の取引のみを表示します。そのフィールドは入力中に自動補完されます。
	<LI><b>日付範囲:</b> 開始日および/または終了日を記入することにより、日付範囲内の取引のみを表示します。
	<LI><b>説明:</b> <i>説明</i>ボックス内に言葉(キーワード)を記入することにより、特定の説明を検索します。「自転車」を記入すると、説明または題名の中に「自転車」という単語があるすべての取引を表示します。
	<LI><b>取引番号:</b> システム内で取引番号が有効な場合、取引番号による検索のためにこのフィールドを使えます。
</UL>
<hr class="help">


<a name="transaction_history_result"></a>
<h3>取引概要結果</h3>
このウィンドウは、支払い検索の結果を表示します。<br> 印刷アイコン<img border="0" src="${images}/print.gif" width="16" height="16">&nbsp;(トップバーのヘルプアイコンの隣にある)をクリックすると、それは${localSettings.maxIteratorResults} の取引および概要レポートが書かれた検索結果の印刷可能なバージョンを表示します。<span class="admin">ローカル設定 &gt; 制限 において、それが表示する取引の数を変更できます。</span> <br>保存アイコン<img border="0" src="${images}/save.gif" width="16" height="16">&nbsp;(トップバーの印刷アイコンの隣に位置する)をクリックすると、その結果をCSVファイルとしてダウンロードできます。<br><br>そのウィンドウの最初の(一番上の)セクションは、以下の情報を表示します:
<ul>
	<li><b>口座残高:</b> 口座残高。
	<li class="member"><b>クレジット下限:</b> クレジット下限。これはゼロまたはマイナスのいずれかになり得ます。(ゼロの場合、その制限は表示されません。)
	<li class="member"><b>クレジット上限:</b> クレジット上限。これはゼロまたはプラスのいずれかになり得ます。(ゼロの場合、その制限は表示されません。)
	<li class="member"><b>利用可能残高:</b> 費やすことができる利用可能残高。
	<li class="member"><b>留保額:</b> これは一時的に留保されており、支払いのために使えない額です。これは、認可を求めて保留中の取引や、来る定期的利子またはデマレージのための留保があるためかもしれません。
</ul>
このセクションの下には、すべての支払い(行ったものと受け取ったもの)のリストが表示されます。 受け取った(入ってきた)支払いは、前に+記号を付けて表示され、出て行った支払いは、前に-記号を付けて表示されます。そのリストは、その支払いの支払日、メンバー名(受取人または支払人)、説明を表示します。メンバー氏名/ログイン名は、メンバープロフィールへのリンクです。<br> 支払いのビューアイコン<img border="0" src="${images}/view.gif" width="16" height="16">&nbsp; をクリックすると、その支払いの説明とともにウィンドウが開きます。
<hr class="help">


<a name="transaction_detail"></a>
<h3>取引詳細</h3>
このウィンドウは、選択された支払いについての詳細を与えます。 印刷アイコンを選択することにより、取引詳細を印刷できます。「親」または「子」の支払いが存在する場合(たとえば、取引手数料が賦課される)、それはこのウィンドウの下に表示されます。<br><br> <span class="broker admin">その取引が認可される必要がある場合、その取引を承諾または拒否することを選ばなければなりません。コメントも記入する必要があります。選択ボックス「メンバーに対して表示する」が選択されている場合、そのコメントはメンバーに見えるようになります。このオプションが選択されていない場合、そのコメントはあなたと管理者にのみ見えるようになります。</span> <span class="admin"> 特定の条件下では、<a href="#charge_back"><u> チャージバック</u></a>から取引を元通りにすることが可能です。この場合、特別なチャージバックボタンがこのウィンドウ内に見えるようになります。 
</ span>の
<hr class="help">


<span class="admin"> <a name="charge_back"></a>
<h3>チャージバック</h3>
管理者(正しい権限のある)は、支払いを「チャージバック」することができます。それは同じ額の反対の支払いが行われるということを意味します。その支払いが他の取引(たとえば、手数料やローン)を生成した場合は、すべての取引がチャージバックされます。
チャージバックが可能な場合、特別なチャージバックボタンが取引詳細ウィンドウ内に見えるようになります。しかしながら、このボタンは、その取引がさほど前ではない時期に行われた場合にのみ見えます。ある取引タイプがチャージバックされ得る最大時間を、「メニュー: 設定 &gt; <a
	href="${pagePrefix}settings#local"><u>ローカル設定 &gt; チャージバック</u></a>」内で定められます。
<hr class="help">
</span>


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