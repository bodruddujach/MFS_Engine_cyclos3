<div style="page-break-after: always;">
	<a name="top"></a>
<br><br>オペレータ機能は、メンバーがオペレータを定められるようにします。オペレータは、Cyclos内でそのメンバーのために一部のタスクを行うことを許された、ある種の下位レベルのメンバーです。
オペレータは自身の口座を持たず、いくつかのオペレーションを行えるメンバー口座にアクセスできるだけです。従業員が3人の小さな会社を考えてください。そこでは、各従業員はその会社の口座のためのオペレータになります。<br> メンバーと同じように、オペレータもグループ内で組織化されます。各メンバーは、自身のオペレータグループを定めることができ、それにより、メンバーは異なる権限を持つ様々なレベルのオペレータを作成できます。たとえば、支払いを行うことを許された1人の上位オペレータと、広告のみを管理できる数人の単純なオペレータを作成できます。オペレータによって行われるすべての支払いには、「実行者」という追加的なフィールドがあり、メンバーはオペレータによって支払いを検索できます。<span class="member notOperator"> 
<i>見つけ方</i><br>
オペレータの機能には、メイン「メニュー: オペレータ」から行けます。このメインメニューセクションの下には、オペレータ機能にアクセスできるいくつかのサブメニューがあります:
<ul>
	<li><b>オペレータ:</b> オペレータの検索ウィンドウに移動します。
	ここで新しいオペレータを作成できます。
	<li><b>接続中のオペレータ:</b> どのオペレータが接続されているかを表示します。
	<li><b>オペレータグループ:</b> 様々なレベルのオペレータを定められます。
	<li><b>カスタムフィールド:</b> オペレータのための特別なフィールドを作成できます。
</ul>
</span>
オペレータは、通常のログインページからログインできます。ログインページには、「オペレータとしてログインする」という追加のリンクが表示されます(このリンクが表示されるようにするには、システム設定内で、オペレータモジュールが有効化される必要があります)。<br>
オペレータにPosWebモジュールへのアクセスを与えることもできます(POSwebについての情報は、
チャネル<a
	href="${pagePrefix}payments#accesing_channels"><u>ヘルプページ</u></a>で見つけられます)。<br><br> <span class="member"> オペレータは、特別なオペレータ用メインメニュー項目から、メンバーに関するアクションを実行できます。それはオペレータだけに見え、「メンバーオペレーション」と呼ばれます。これにより、オペレータは、通常のメンバーであれば「メインメニュー: パーソナル」項目の下に収まることになるすべての機能にアクセスできます。</span> <br> <span class="admin"> <i>動かし方?</i><br> オペレータは、まず、グループの<a href="${pagePrefix}groups#manage_group_permissions_member"><u>権限</u></a>、「オペレータ」ブロック、「オペレータを管理する」という表題のチェックボックスから有効化されなければなりません。<br> オペレータがログインできるようには、権限の設定に加え、<a href="${pagePrefix}settings#access"><u> アクセス設定</u></a> 内で「オペレータログインを可能にする」チェックボックスから有効化されていることを確認してください。<br><br><b>注:</b> ログインページをカスタマイズするならば、オペレータのログインを可能にするために使われるコードを維持するように気をつけてください。さもないと、オペレータログインリンクが見えなくなるかもしれません。<br><br><b>注2:</b> オペレータモジュールとオペレーションは、完全にメンバーの責任です。管理者がメンバーのためにオペレータを管理することはできません。管理者が行える唯一の管理は、<a href="${pagePrefix}user_management#connected_users_result"><u>接続中のユーザー</u></a>ページからオペレータを切断することです。</span>
<hr>


<span class="member notOperator">
<a name="search_operator"></a>
<h3>オペレータ検索</h3>
このページでは、オペレータ(あなたが登録した)を検索できます。 その機能は、一般のメンバー検索機能と同じように機能します。グループ選択ボックスでは、「すべてのグループ」フィルタを残すか、検索したい1つまたは複数のグループを選択できます。<br> 検索結果を表示するには、「検索」をクリックしてください。<br><br>新しいオペレータを作成することもできます。 これはこのウィンドウの下のドロップダウンボックス(「新しいオペレータを作成する」)内でオペレータグループを選択することにより行われます。このドロップダウンボックスは、検索結果ウィンドウが1つもない場合にのみ見えます。
<hr class='help'>
</span>


<span class="member notOperator"> <a name="search_operator_result"></a>
<h3>オペレータ検索結果</h3>
このページは、オペレータ検索の結果リストを表示します。 オペレータの氏名またはログイン名をクリックすると、プロフィールページが開きます。
<hr class="help">
</span>


<span class="member notOperator"> <A NAME="create_operator"></A>
<h3>オペレータを作成する</h3>
このページでは、新しいオペレータを作成できます。 赤いアスタリスク(*)の印が付いたフィールドは、すべて必須です。<br> フィールドに記入した後、直接そのプロフィールに行くか(「保存してオペレータのプロフィールを開く」ボタン)、新しいオペレータを追加する(「保存して新しいオペレータを挿入する」ボタン)ことができます。
<hr class='help'>
</span>


<a name="operator_profile"></a>
<span class="member">
<h3>オペレータプロフィール</h3>
このウィンドウは、オペレータのプロフィールを表示します。 たいていのフィールドは変更できませんが、変更できるものもあります。変更するには「変更」ボタンをクリックし、変更を保存するには「送信」をクリックしてください。</span>
<span class="member notOperator">
<br><br>この画面をチェックしている瞬間に、ちょうどこのオペレータがログインしていれば、そのことが通知されます。「前回のログイン」フィールドが、「ログイン中」と(赤で)表示されます。
<hr class='help'>
</span>


<span class="member notOperator">
<A NAME="actions_for_operator_by_member"></A>
<h3>オペレータのためのアクション</h3>
ここでは、このオペレータに関していくつかのアクションを実行できます。 このヘルプは、すべてのアクションをについて要約しています。アクションについてのより詳細な説明については、具体的なアクション内のヘルプを参照できます。<br><br>以下のアクションが利用可能です:
<UL>
	<LI><b>権限グループを変更する:</b> このオペレータが属するオペレータグループを変更します。
	<LI><b>Eメールを送信する:</b> オペレータにEメールを送信します。 これはあなたのローカルのEメールプログラムを開きます。
	<LI><b>ログインパスワードを管理する:</b> オペレータのためのパスワードを変更します。
	<li><b>ユーザーが現在ログインするのを許可する:</b>これは、オペレータが誤ったパスワードでのログインを数回試み、そのためにそのアカウントが一時的にブロックされた場合にのみ見えます。通常、許される試行の最大数があります。誤ったパスワードで、それよりも頻繁にログインしようと試みると、あなたのアカウントは管理者によって設定された期間、一時的にブロックされます。このオペレータが本人であると確信しているならば、このボタンをクリックすることによって、直ちに再びログインできるようにすることができます。その場合、そのオペレータは、このための通常の待ち時間が過ぎるまで待つ必要がありません。
	<li><b>ログイン中のユーザーを切断する:</b> これは、オペレータが、ちょうどこの瞬間にログイン中の場合にのみ見えます。これは、上のプロフィールウィンドウ内の「最終ログイン」フィールドによっても示されます。それは「ログイン中」という言葉を表示します。この場合、このボタンをクリックすることにより、そのオペレータを直ちにプログラムから締め出すことができます。たとえば、不正利用に関する調査を待っている場合や、オペレータが既にログイン中であるとシステムが判断しログインできない場合に、これを行いたいかもしれません。
</UL>
<hr class='help'>
</span>


<span class="member notOperator">
<A NAME="manage_operator_groups"></A>
<h3>オペレータグループを管理する</h3>
このページは、<a href="#top"><u>オペレータ</u></a>グループのリストを表示します。ここでは以下のアクションを行えます:
<ul>
	<li><img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp; 編集アイコンを押すと、このグループのための設定のページに移動します。
	<li><img border="0" src="${images}/permissions.gif" width="16" height="16">&nbsp; 権限アイコンを押すと、このグループのための権限を設定できるページに移動します。そのグループが「削除済み」状態の場合、このアイコンは無効になります(灰色になります<img border="0" src="${images}/permissions_gray.gif" width="16" height="16">&nbsp;)。
	<li><img border="0" src="${images}/delete.gif" width="16" height="16">&nbsp; 消去アイコンをクリックすると、そのグループを削除します。その中に1人もメンバー(オペレータ)がいない場合に限り、グループを消去できます。
	<li><b>追加:</b> 新しいオペレータグループを追加するには、「新しいグループを挿入する」というラベルの送信ボタンをクリックしなければなりません。
</ul>
<hr class='help'>
</span>


<span class="member notOperator">
<A NAME="manage_group_permissions_basic"></A>
<h3>基本的なグループ権限</h3>
このウィンドウでは、基本的な権限を設定できます。 基本的な権限は、他の機能には影響しません。たとえば、オペレータがログインできない場合でも、依然として支払いを受け取ることはできるかもしれません。以下の権限を設定できます:
<ul>
	<li><b>ログイン: </b><br> これがチェックされていない場合、このグループのオペレータはログインできません。
	<li><b>招待メッセージ: </b><br> これがチェックされている場合、このグループのメンバーはメインページ(ログイン後の)でウィンドウボックスを目にします。それにより、友人を招待し、あなたの組織を試してもらうことができます。
</ul>
<hr class='help'>
</span>


<span class="member notOperator"> <A
	NAME="manage_operator_group_permissions"></A>
<h3>オペレータグループ権限を管理する</h3>
このウィンドウでは、<a href="#top"><u>オペレータ</u></a>グループのための<a href="${pagePrefix}groups#permissions"><u> 権限</u></a>を設定できます。これらの権限は、あなた自身のグループの権限に由来します。つまり、オペレータは、あなた自身が許されている以上のことは決してできません。オペレータは、それと同等かそれ未満のことしかできないのです。<br> このため、おそらく、このヘルプ概観にリストで示されたオプションのすべてを見ることはできません。この項目についてさらなる情報を得るには、リンクを使ってください。
<br><br>このグループに属するオペレータは、これらの権限を受け取るかもしれません(システム設定とあなた自身の権限に依存します):<br><br> <b>メンバー口座</b>
<ul>
	<li><b>認可された支払いを見る</b>
	<li><b>スケジュールされた支払いを見る</b>
	<li><b>口座情報を見る:</b> ドロップダウンボックスを使って、オペレータが情報(支払、残高など)を見られる口座を選択してください。
</ul>
<b>広告</b>
<ul>
	<li><b>掲載する</b> 「掲載する」が選択された場合、オペレータは広告を掲載することができ、メニュー項目「パーソナル - 広告」がオペレータメニュー内に表示されます。
</ul>
<b>連絡先リスト</b>
<ul>
	<li><b>管理する:</b> オペレータが<a href="${pagePrefix}user_management#contacts"><u>連絡先リスト</u></a>を管理する、つまり、リストのメンバーを追加、編集、消去するのを可能にします。
	<li><b>見る:</b> オペレータが連絡先を見る(そして使う)ことだけを可能にしますが、それを変更する権限はありません。
</ul>
<b>保証書</b><br><br> これはCyclosの保証書システムの一部です。そこでは、Cyclos内の各口座残高が、裏付けされた貨幣総額によって保証されています。あなたは以下の権限を選べます:
<ul>
	<li><b>支払債務で購入する</b></li>
	<li><b>支払債務で販売する</b> 
	<li><b>許された保証書タイプ(証明のため)</b></li>
	<li><b>証明書を発行する</b></li>
	
</ul>
<b>請求書</b> <br> このセクションでは、オペレータが他のメンバーに対して<a href="${pagePrefix}invoices"><u>請求書</u></a>を送信(ユーザープロフィールから、または、直接「口座」メニューから)できるかどうかを定められます。「システム請求書」が選択された場合、オペレータは「口座メニュー」からシステム口座に請求書を送信できます。
<ul>
	<li><b>メニューからのメンバー請求書オプション:</b> メニューからメンバーへの請求書オプションを表示します。
	<li><b>メンバーに送信する:</b> 他のメンバーへの請求書の送信を可能にします。
	<li><b>システムに送信する:</b> システム口座への請求書の送信を可能にします。
	<li><b>見る:</b> 請求書を見る。
</ul>
<li><b>ローン:</b> このセクションでは、オペレータのために<a href="${pagePrefix}loans"><u>ローン</u></a>についての権限を定められます。
<ul>
	<li><b>閲覧する:</b> 「閲覧する」オプションが選択された場合、そのグループのオペレータはそのローンを閲覧できます。閲覧が選択されていなければ、そのメニュー項目は表示されません。
	<li><b>返済する:</b> オペレータがローン返済を行えるようにするには、これを選択してください。
</ul>
<li><b>メッセージ:</b> このセクションでは、オペレータがCyclosの<a href="${pagePrefix}messages"><u>メッセージ</u></a>システムをどの範囲まで使って良いかを定められます。
<ul>
	<li><b>見る:</b> オペレータはメッセージシステムを見られます。
	<li><b>メンバーに送信する:</b> オペレータは他のメンバーにメッセージを送信できます。
	<li><b>管理者に送信する:</b> オペレータは管理者にメッセージを送信できます。 
	<li><b>管理する:</b> オペレータはメッセージを移動および消去できます。
</ul>
<b>支払い:</b> ここでは、このオペレータグループについて、どのタイプの支払いが可能かを指定できます。
たいてい、あなたは1つのみ、または2,3のタイプを選択するでしょう。
<ul>
	<li><b>自己支払い:</b> これが選択された場合、オペレータはあなた自身の口座間で支払いを行えます。ドロップダウン内で、可能な取引タイプを指定できます。このオプションは、あなたが複数のオペレータ口座を持っている場合にのみ意味をなします。
	<li><b>メンバー支払い:</b> 選択された場合、オペレータは別のメンバーに支払いを行えます。
	<li><b>メニューからのメンバー支払いオプション:</b> このオプションがチェックされている場合、オペレータは「口座」メニューから他のメンバーに直接支払いを行えます。
	<li><b>システム支払い:</b> 選択された場合、オペレータはシステム口座に対して支払いを行えます。このオプションが選択されていない場合、メニュー項目「システム支払い」は表示されません。
	<li><b>PosWeb支払いをする:</b> オペレータがPOSweb(Point of Sale)ページで支払いを行えるようにします。
	<li><b>PosWeb支払いを受け取る:</b> オペレータがWEBposから支払いを受け取れるようにしたい場合は、このオプションを選んでください。これは典型的に店舗における状況でしょう。カウンターにいる店員は、オペレータとして(通常、http://..cyclos/poswebから)PosWebインタフェースにログインします。それに続くウィンドウでは、顧客がその店舗に支払いをするためにPINを入力できます。<br> このチェックボックスをチェックすると、この手続きが可能になります。(通常はそれからそのオペレータの他のすべての権限を無効にするでしょう。)
	<li><b>認可または拒否する:</b> あなたが受取人の場合、オペレータが支払いを認可または拒否できるようにします。
	<li><b>支払い認可をキャンセルする:</b> 認可された支払いが使われる場合、これはオペレータが認可された支払い(いったん作成されたが未認可のもの)をキャンセルできるようにします。
	<li><b>スケジュールされた支払いをキャンセルする:</b> スケジュールされた支払いが使われる場合、これは計画された日付が始まる前に、オペレータがスケジュールされた支払いをキャンセルできるようにします。
	<li><b>スケジュールされた支払いをブロックする:</b> オペレータが、スケジュールされた支払いを一時的にブロックできるようにします。
	<li><b>他のチャネルからの支払いをリクエストする:</b> これがチェックされた場合、オペレータは他のチャネルから、支払いリクエスト(外部請求書)を送信できます。ドロップダウンボックスからそれらのチャネルを選べます。
</ul>
<b>評価</b> <br><br> これにより、オペレータは<a href="${pagePrefix}references"><u>評価</u></a>を閲覧または管理できます。
<ul>
	<li><b>見る:</b> 評価を見ます。
	<li><b>自分の評価を管理する:</b> オペレータが、他のメンバーに評価を与える権限も含め、評価システムを使えるようにします。
	<li><b>自分の取引フィードバックを管理する:</b> オペレータが、取引に関するフィードバックを与える権限も含め、あなたの<a href="${pagePrefix}transaction_feedback#feedbacks_summary"><u>取引フィードバック</u></a>を管理できるようにします。
</ul>
<b>レポート</b><br> 「自分のレポートを見る」が選択された場合、オペレータは、あなた自身の<a href="${pagePrefix}reports#member_activities"><u>レポートページ</u></a>を見られます。
</ul>
<hr class='help'>
</span>


<span class="member notOperator"> <a name="edit_operator_group"></a>
<h3>オペレータグループを修正する</h3>
<a href="#top"><u>オペレータ</u></a>グループ設定は、2つの部分に分けられます:
<ul>
	<li><b>グループ詳細:</b> ここでは、オペレータグループの名称と説明のみを変更できます。
	<li><b>支払いタイプごとの1日ごとの最大額:</b> これらの設定により、支払いタイプごとの1日ごとの最大額を定められます。すべての利用可能な支払いタイプがここにリストで示されます。各々のタイプについて、オペレータがこの支払いタイプとして支払える額に制限があるか否かを指定できます。
</ul>
「グループ権限」をクリックすることにより、このグループの権限に直接移動できます。
<hr class='help'>
</span>


<span class="member notOperator">
<A NAME="insert_operator_group"></A>
<h3>新しいオペレータグループを挿入する</h3>
このウィンドウにより、新しい<a href="#top"><u>オペレータ</u></a>グループを作成できます。<br>
以下のオプションがあります
<ul>
	<li><b>削除済み:</b> 削除済みグループを作成するということは、このグループに入れられたオペレータが本当にシステムを去ったということを意味します。いったん削除済みグループに入れると、どの他のグループにも戻すことができません。そのデータはまだデータベース内にあって閲覧できますが、バックアップ機能としてのみ役立ちます。
	<li><b>名称:</b> グループの名称。
	<li><b>説明:</b> そのグループの説明。
	<li><b>次から設定をコピー:</b> これは、定められた別のオペレータグループが既にある場合にのみ見えます。ここで別のオペレータグループを指定できます。そうすると、新たに作成されるグループのための設定は指定したグループからコピーされます。
</ul>
新しいグループを作成した後、次の画面でグループ特性を設定し、そのグループの権限も設定してください。
<hr class='help'>
</span>


<span class="member notOperator">
<a name="manage_group_customized_files"></a>
<h3>カスタマイズされたPosWeb</h3>
PosWebのためのヘッダーとフッターを定められます。
このウィンドウは、このグループのためのカスタマイゼーションのリストを表示します。
以下のオプションがあります:
<ul>
	<li><b>修正する:</b> <img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp;編集アイコンから、既存のカスタマイズされたファイルを修正します。
	<li><b>閲覧する:</b> <img border="0" src="${images}/view.gif" width="16" height="16">&nbsp;ビューアイコンによって、そのグループのメンバーにその結果がどのように見えるかを閲覧します。
	<li><b>消去する:</b> <img border="0" src="${images}/delete.gif" width="16" height="16">&nbsp;消去アイコンから、カスタマイズされたファイル定義を消去します。
	<li><b>挿入する</b> 「新しいファイルをカスタマイズする」ボタンからカスタマイズされた新しいファイルを挿入します。 
</ul>
<hr class="help">
</span>


<span class="member notOperator"> <a name="customize_group_file"></a>
<h3>PosWebのヘッダーとフッターを修正する</h3>
このページでは、PosWebページのヘッダーとフッターをカスタマイズできます。 このページは、オペレータが支払いを行ったり受け取ったりするためにアクセスできます。(PosWebのURLは、たいてい、www.domain.com/cyclos/poswebのようなものです。)<br> オペレータがログインした後、ヘッダーとフッターが支払いウィンドウの上下に表示されます。
<hr class="help">
</span>


<span class="member notOperator">
<A NAME="change_group_operator"></A>
<h3>オペレータグループを変更する</h3>
このウィンドウでは、別のグループに <a href="#top"><u>オペレータ</u></a> を移せます。これは、そのオペレータが他のグループの権限を受け取ることを意味します。フォームに記入した後、変更を保存および確定するために「グループを変更する」ボタンをクリックしてください。<br> <br><br>「永久的に削除する」オプションをクリックすると、オペレータが削除されます。これは、そのオペレータがまだどのような取引も行っていない場合にのみ可能です。<br> そうでない場合は、それを「削除済み」グループへと移動しなければなりません。これにより、そのオペレータはいかなる追加のアクションも(ログインさえも)行えなくなりますが、あなたは依然としてそのオペレータの過去のアクションを閲覧できます。
<hr class='help'>
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