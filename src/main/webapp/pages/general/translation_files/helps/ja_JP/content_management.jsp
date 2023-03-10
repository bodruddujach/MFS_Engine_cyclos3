<div style="page-break-after: always;">
<span class="admin"> <a name="top"></a>
<br><br>Cyclosでは、任意のCyclosシステムファイルを修正することにより、ユーザーインタフェースを変更できます。さらに、修正を.themeファイルにパッケージ化して、他のCyclosユーザーと共有することが可能です。<br>
このことが意味するのは、事実上、あなたは基本的に、あなたやメンバーがブラウザで見るページのすべてを制御できるということです。つまり、コンテンツと外観は操作可能です。<a name="type_list"></a> あなたは以下のタイプのファイルをカスタマイズできます:
<ul>
	<li><b><a href="#statics"><u>静的ファイル</u></a> </b>
	<li><b><a href="#helps"><u>ヘルプファイル</u></a> </b>
	<li><b><a href="#jsp"><u>アプリケーションページ</u></a></b>
	<li><b><a href="#css"><u>CSSファイル</u></a></b>
	<li>これらに加えて、<a href="#images"><u>画像</u></a>もカスタマイズできます。	
	<li><b><a href="#themes"><u>テーマ</u></a>:</b> あらゆる種類のファイルを変更するという面倒なしに、別の事前に定義されたレイアウトに切り替えられます。
</ul>
<b>重要:</b> これらの種類のファイルの編集は込み入った仕事になることに留意してください。それを行うには、CSSとHTMLについての知識が必要になるでしょう。
<br><br>システムレベル(このヘルプファイルにより扱われている)においてだけでなく、<a href="${pagePrefix}groups#manage_group_customized_files"><u>グループレベル</u></a>においても、そして、<a href="${pagePrefix}groups#manage_group_filter_customized_files"><u>グループフィルタレベル</u></a>においてさえも、カスタマイズされたファイルを設定できることに留意してください。<br><br><i>見つけ方</i><br> システムの広範なレベルにおけるコンテンツ管理には、メインメニュー、項目「コンテンツ管理」から行けます。<br> グループレベルでコンテンツ管理を行うには、<a href="${pagePrefix}groups#manage_groups"><u>グループ管理ウィンドウ</u></a>に行き、<img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp; グループの編集アイコンをクリックしてください。この主題は、グループヘルプファイル内で扱われています。<br>
グループフィルタレベルでコンテンツ管理を行うには、「メニュー:
ユーザーとグループ &gt; グループフィルタ」に行き、<img border="0"
	src="${images}/edit.gif" width="16" height="16">&nbsp; グループフィルタの編集アイコンをクリックしてください。またもや、この主題は、グループヘルプファイル内で扱われています。<br><br><i>動かし方</i><br> コンテンツ管理に関するメニュー項目が表示されるようにするには、<a href="${pagePrefix}groups#manage_group_permissions_admin_system"><u> 管理者権限</u></a>を設定する必要があります。「メニュー: コンテンツ管理」内の項目の可視性に影響する項目がいくつかあります。以下のブロックが当てはまります:
<ul>
	<li>「カスタマイズされた画像」
	<li>「システム全般のカスタマイズされたファイル」
	<li>「テーマ」
</ul>
<hr>


<a name="statics"></a>
<h3>静的ファイル</h3>
これらは、あなたの組織に合わせてカスタマイズできるアプリケーション内の完全なページです。<br> これらのファイルを修正するには、HTMLの知識が必要とされます。HTMLタグを完全に残し、アプリケーションタグ内のコンテンツを修正しないように気をつけてください(タグはすべて&lt;と&gt;の中にあります)。<br><br>その機能により、以下の静的ファイルの修正が可能になります(もっと多くのファイルが追加されているかもしれないため、このリストは古いかもしれません):
<ul>
	<li><b>contact.jsp:</b> メンバーセクション 「メニュー: ヘルプ &gt; 連絡先」のメニュー内に表示されるページで、あなたの組織に連絡する方法に関する情報を示すものです。
	<li><b>general_news.jsp:</b> Cyclosのメンバーセクション内のホームページにある<a href="${pagePrefix}home#home_news"><u>一般的なお知らせ(メッセージボード)</u></a>ウィンドウに表示される一般的なお知らせメッセージ。
	<li><b>login.jsp:</b> ログインページのレイアウト(ログインページのテキストは、翻訳機能で修正できます)。
	<li><b>member_about.jsp:</b> メンバーセクションと管理者セクションのメニュー「メニュー: ヘルプ &gt; Cyclosについて」に表示されるページ。
	<li><b>posweb_footer.jsp:</b> 外部支払いモジュールPosWebのページフッター。(メンバーによりカスタマイズ可能)
	<li><b>posweb_header.jsp:</b> 外部支払いモジュールPosWebのページヘッダー。(メンバーによりカスタマイズ可能)
	<li><b>posweb_login.jsp:</b> 外部支払いモジュールPosWebのログインページヘッダー。
	<li><b>top.jsp:</b> アプリケーションの一般的な見出し。
	<li><b>webshop_footer.jsp:</b> 外部支払いモジュールWebショップのページフッター。
	<li><b>webshop_header.jsp:</b> 外部支払いモジュールWebショップのページヘッダー。
</ul>
静的ファイルの本文内に<a href="#insert_images"><u>画像</u></a>を挿入することもできます。
<hr class="help">


<a name="helps"></a>
<h3>ヘルプファイル</h3>
それが帰属する<a href="#jsp"><u>アプリケーションファイル</u></a>を変更した場合や、単に元の本文が十分に明瞭でないと思う場合、ヘルプファイルを変更したいかもしれません。<br> ヘルプファイルを修正したければ、その名称とそのファイル内のジャンプ先のタグを知っている必要があります。ヘルプファイルは、主な主題ごとに組織化されています。 約30のヘルプファイルの各々がいくつかのヘルプウィンドウのテキストを含んでおり、タグからこれらのウィンドウテキストにジャンプします。<br> ファイル名とタグ名を見つけるには、マウスポインタをヘルプアイコン(各ウィンドウの右上)に重ねなければなりません。あなたのブラウザのステータスバーに位置「Help: file_name#tag_name」が表示されます。
この場合、ファイルは「file_name.jsp」で、ヘルプファイル内のセクション(タグ)は「tag_name」と呼ばれます。ヘルプファイル内で、このタグ名は&lt;a name=&quot;tag_name&quot;&gt;...&lt;/a&gt;タグの中に置かれます。<br>
あなたのブラウザは、ステータスバーメッセージを隠すかもしれないことに留意してください。上記の場所にヘルプファイルの位置が表示されない場合は、ブラウザの設定を変更して、「ステータスバーメッセージを表示する」設定がされていることを確認してください。<br><br>あなたは、ヘルプファイルの本文内に<a href="#insert_images"><u>画像</u></a>を挿入してもよいですが、ヘルプウィンドウは300×400ピクセルしかないので、画像サイズに注意してください。
<hr class="help">


<a name="jsp"></a>
<h3>アプリケーションページ</h3>
アプリケーションページは、ページ上に要素を配置するためのコードを含むが本文は含まないCyclos内の.jspファイルです。事実上、これは、いかなるjspファイルもヘルプ本文を含まないということを意味します。これらの.jspファイルは、あなたがブラウザで見るページ内のどこに何が表示されるかを定義します。<br> レイアウト構造全体を修正できますが、それはシステムの内部変数とアプリケーションの機能に深刻な影響を与える可能性があります。したがって、アプリケーションファイルを修正する際には慎重に行ってください。小さなレイアウト変更のためにのみ、それらを修正することが推奨されます。たとえば、要素の順序を変更したい場合や、ある要素がページ内に見えないようにしたい場合です。また、それを行う前に、あなたの目的が、プログラムの管理者セクションから通常のCyclos設定によって達成できないかどうか確認してください。<br><br>問題を避けるために、Cyclosは常に容易に入れ替えられる<a href="#edit_customized_file"><u>元のページ</u></a>のコピーを保持しています。<br><br>また、jspファイルのコンテンツ内に<a href="#insert_images"><u>画像</u></a>を挿入することもできます。
<hr class="help">


<a name="css"></a>
<h3>CSSファイル</h3>
CSSファイル(カスケーディング・スタイルシート)は、ページ内の諸要素がどのように見えるかを定義します。特定の要素の見え方を変えたい場合は、これを変更したいかもしれません。たとえば、ボタンをもっと強調したり、メニュー項目をもっと明るくしたりする場合です。スタイルシートを修正するには、CSSファイル注釈の知識が必要とされます。<br><br>Cyclosには以下のスタイルシートファイルがあります:
<ul>
	<li><b>style.css</b><br> Cyclosのレイアウト(メニュー、ウィンドウ、トップ)のための主要なスタイルシートファイルです。
	<li><b>login.css</b><br> ログインページのためのスタイルシート。
	<li><b>mobile.css</b><br> モバイルページのためのスタイルシート。
	<li><b>posweb.css</b><br> PosWebページのためのスタイルシート。
	<li><b>ieAdjust.css</b><br> Internet Explorerとの互換性問題を解決するために使われます。
</ul>
<br><br><br>CSSファイル修正の効果を見るためには、ブラウザでページを更新しなければならないかもしれません。これはURL「www.yourdomain.org/cyclos/pages/styles/style.css」に行くことにより行えます。<br> CSSのコンテンツは、テキストとして表示されます。新しいページがアクティブであるかどうかを確認するために、ブラウザでそのページを数回更新することができます。<br><br>新しいCSSファイルを使いたい場合は、(スタイルシート編集ウィンドウ内で)コンテンツ全体をコピーし、それを既存のスタイルシートに上書きして、上記のようにページを更新してください。<br><br>素敵なCSSファイルを作り上げた場合は、是非、私たちにそれを送ってください。 そうすると、私たちはそれを他のユーザーからも利用可能なようにすることができます。そのスタイルシートは、Sourceforgeとプロジェクトのサイトで公開されるでしょう。
<hr class="help">


<a name="insert_images"></a>
<h3>テキストファイル内の画像</h3>
<a href="#statics"><u>静的ファイル</u></a>や<a href="#helps"><u>ヘルプ</u></a>ページのようなテキストファイル内には画像を挿入できます。これを行うには、画像がアプリケーションから利用可能でなければなりません。どの<a
	href="#system_images"><u>システム画像</u></a>が利用可能かをチェックしたり、「メニュー: コンテンツ管理 &gt; カスタム画像」から、自分で画像をアップロードしたりすることができます。ページ内に画像を挿入するには、静的ファイルの最初に以下のタグが置かれる必要があります:<br><br><i>&lt;%@ taglib
uri="http://jakarta.apache.org/struts/tags/struts-html" prefix="html"
%&gt;</i>
<br><br>そして、画像の配置はこのようになります:<br><br><i>&lt;img src="&lt;html:rewrite
page="/pages/images/some_image.jpg"/&gt;"&gt;</i>
<br><br>画像の配置のためにページに必要なのは、「html:rewrite」タグのみです。border, align, width, heightのような通常のHTMLアーギュメントを使えます。
<hr class="help">


<a name="customized_files"></a>
<h3>カスタマイズされたファイル</h3>
<b>注:</b> このヘルプファイルは、このフォームの機能に関する一般的な情報しか示しません。あなたがカスタマイズしたい種類のファイルに関する具体的な情報やヒントについては、<a href="#type_list"><u>このリスト</u></a>をチェックして、そのファイルタイプのリンクを辿ることができます。<br><br>このウィンドウは、<a href="#top"><u>カスタマイズ</u></a>されているファイルのリストを表示します。
以下のオプションがあります:
<ul>
	<li><b>新しいファイルをカスタマイズする:</b> 「新しいファイルをカスタマイズする」というラベルの送信ボタンをクリックすることにより、新しいファイルをカスタマイズしてください。
	<li><img border="0" src="${images}/preview.gif" width="16" height="16">&nbsp; ビューは、結果のプレビューを可能にします。
	<li><img border="0" src="${images}/edit.gif" width="16" height="16">&nbsp; カスタマイズされたファイルを編集できます。
	<li><img border="0" src="${images}/delete.gif" width="16" height="16">&nbsp; 消去アイコンをクリックすることにより、カスタマイズされたファイルがリストから消えます。そのファイルの最新の修正は見える状態のままですが、Cyclosの最初のアップデートの際に標準のページによって置き換えられます。
</ul>
注1: ファイル名が赤色で表示される場合、それは競合があるという意味です。競合を処理する方法についての説明は、<a href="#edit_customized_file"><u>カスタマイズされたファイルを編集する</u></a>ヘルプページで見つけられます。<br> 注2:この機能を初めて使う際には、リスト内に1つもカスタマイズされたファイルがないかもしれません。その場合には、アイコンが1つも見えないでしょう。これはカスタマイズしたいファイルのタイプに依ります。
<hr class="help">


<a name="edit_customized_file"></a>
<h3>カスタマイズされたファイルを編集する</h3>
<b>注:</b> このヘルプセクションは、このフォームの機能に関する一般的な情報しか示しません。<a href="#top"><u>カスタマイズ</u></a>したい種類のファイルに関する具体的な情報やTipsについては、<a href="#type_list"><u>このリスト</u></a>をチェックして、そのファイルタイプのリンクを辿ることができます。<br><br>このウィンドウでは、あなたが以前カスタマイズしたファイルを再び修正できます。いつものように、「変更」ボタンをクリックしてフィールドの修正を始め、「送信」をクリックして変更を保存してください。
<ul>
	<li><b>ファイル名:</b> ファイル名を示します。 これは修正できません。
	<li><b>コンテンツ:</b> このエリアはそのファイルの現在のコンテンツを表示します。 ここでは、そのファイルのコンテンツを作成/修正できます。HTMLタグとXMLタグを使えるので、たとえば、Cyclos内の他のセクションへのクイックリンクのついたツールバーのある「一般的なお知らせ」ページのような多くのことが可能です。また、振る舞いを定義するためにJavascriptを追加しても良いでしょう。 ただし、これは高度なプログラミングです。それについては、<a href="http://project.cyclos.org/wiki/index.php?title=Programming_guide#JSP"><u>Cyclosプログラミングガイド</u></a>を参照してください。
	<li><b>元のコンテンツ:</b> このファイルがカスタマイズされる前の元のコンテンツを表示します。<a href="#customized_files"><u>リスト</u></a>からカスタマイズされたファイルを消去すると、元のコンテンツが再び適用されます。もちろん、あなたが定めたコンテンツが機能しないようであれば、「元のコンテンツ」の内容を「コンテンツ」ボックスにコピー/貼り付けすることができます。
	<li><b>新しいコンテンツ:</b> Cyclosをアップデート/アップグレードし、新しいバージョンのファイルがある場合には、Cyclosはそのファイルを新しいコンテンツで置き換えずに(システム)アラートを生成します。新しいコンテンツは、このエリア内に得られます。
	<li><b>競合を解決する:</b> カスタマイズされた新しいバージョンのファイルがある場合、Cyclosはシステムアラートを生成し、その新しいバージョンを「新しいコンテンツ」エリア内に置きます。また、「競合している」カスタマイズされたファイルの名称が、カスタマイズされたファイルのリスト内に赤色で現れます。<br> その競合が解決され、すべてが正しく機能すると、「競合を解決する」オプションを選択して、そのファイルを保存できます。これを行うと、そのファイル名はファイルリスト上で赤く表示されなくなり、そのファイルの新しいコンテンツが元のコンテンツエリへと移されます。 	
</ul>
<hr class="help">


<a name="edit_new_customized_file"></a>
<h3>(新しい)ファイルをカスタマイズする</h3>
このウィンドウでは、<a href="#top"><u>カスタマイズ</u></a>を始めるファイルを選べます。
「ファイル選択」ドロップダウンで、カスタマイズしたいファイルを選んでください。このドロップダウン内に直接リスト表示されたファイルを見るか、これらのファイルが置いてあるかもしれないディレクトリを見ることができます。ディレクトリやサブディレクトリの場合には、「ファイル選択」ドロップダウン選択でディレクトリツリーをブラウズできます。ディレクトリは、選択ボックスの上にある「パス」フィールド内にリスト表示されます。選択ボックスの隣の「上へ」リンクを選択することにより、もっと上のディレクトリに移動できます。ファイルを含むディレクトリに辿り着くと、ファイルを選択でき、そのコンテンツが下のテキストエリア内に表示されます。<br> それから「変更」ボタンをクリックすると、そのファイルを編集できます。「送信」ボタンをクリックして、あなたの行った変更を保存してください。
<br><br>変更済みのカスタマイズしたファイルを保存する際には、元のコンテンツは保存され、カスタマイズされたファイルの下に表示されます。アップグレードがインストールされた場合、Cyclosはカスタマイズされたファイルを保持しますが、元のコンテンツとアップグレード内の新しいファイルのコンテンツに差異があるかどうかチェックします。もし、差異があれば、元のコンテンツの下に新しいコンテンツが置かれます。「解決する」を選択すると、新しいコンテンツが元のコンテンツのエリアへと移動します。<br><br>ファイルのカスタマイズを(それをリストから除くことにより)止めると、元のコンテンツが使われます。<hr>


<a name="images"></a>
<h2>画像のカスタマイズ</h2>
Cyclos内の画像をカスタマイズすることもできます。 一組のシステム画像がありますが、独自の画像をアップロードして、任意のカスタマイズされたファイル内で使い始めることもできます。「メニュー: コンテンツ管理」から、あなたの画像をアップロードできます。そこでは、<a href="#system_images"><u>システム画像</u></a>を変更したり、独自の<a href="#custom_images"><u>カスタム画像</u></a>をアップロードしたり、<a href="#style_images"><u>スタイルシート画像</u></a>をアップロードしたりすることができます。
<hr class="help">


<A NAME="system_images"></A>
<h3>システム画像</h3>
<br><br>このウィンドウは、Cyclos内の現在のシステム<a href="#images"><u>画像</u></a>のリストを表示します。リスト内の画像サムネイルをクリックすると、ポップアップ・ウィンドウ内に実寸大の画像を表示します。下の<a href="#images_upload"><u> 更新ウィンドウ</u></a>内で、システム画像を置き換えられます。
<hr class="help">


<A NAME="custom_images"></A>
<h3>カスタム画像</h3>
<br><br>このウィンドウは、Cyclos内のカスタム<a href="#images"><u>画像</u></a>のリストを表示します。リスト内の画像サムネイルをクリックすると、ポップアップ・ウィンドウ内に実寸大の画像を表示します。カスタム画像は、<a href="#statics"><u>静的ファイル</u></a>、<a href="#helps"><u>ヘルプファイル</u></a>、そして、メッセージの中でも使えます。<br><br><img border="0" src="${images}/delete.gif" width="16" height="16">&nbsp;消去アイコンを選択して、画像を消去できます。<br> 下の<a href="#images_upload"><u> 更新ウィンドウ</u></a>内で、カスタム画像を追加できます。
<hr class="help">


<A NAME="style_images"></A>
<h3>スタイルシート画像</h3>
<br><br>スタイルシート画像は、ウィンドウ見出し、メニュー項目、ボタン、背景のようなCyclosのレイアウト内で使える<a href="#images"><u>画像</u></a>です。あなたはこれらの画像を<a href="#css"><u>CSSファイル</u></a>内で使うでしょう。<br><br><img border="0" src="${images}/delete.gif" width="16" height="16">&nbsp;消去アイコンを選択して、画像を消去できます。<br> リスト内の画像サムネイルをクリックすると、ポップアップ・ウィンドウ内に実寸大の画像が表示されます。下の<a href="#images_upload"><u>更新ウィンドウ</u></a>内でカスタム画像を追加できます。
<hr class="help">


<A NAME="images_upload"></A>
<h3>画像アップロード</h3>
<br><br><a href="#system_images"><u>システム画像</u></a>の場合は、まず、「新規アップロード」ドロップダウンボックス内の上のリストから名称を選ぶことにより、どの画像を置き換えたいかを選択してください。このドロップダウンは、<a href="#custom_images"><u>カスタム画像</u></a>または<a href="#style_images"><u>スタイルシート画像</u></a>のアップロード中には見えないことに留意してください。<br> それから、「ブラウズ」ボタンをクリックして、ローカルコンピュータまたはネットワーク上のアップロードしたい画像を探してください。その拡張子がjpg, jpeg, gif, pngであることを確認してください。この後、「送信」をクリックしてください。 新しい画像が上のリストウィンドウ内に現れます。
<hr>


<a name="themes"></a>
<h2>テーマ</h2>
テーマは、時には「スキン」と呼ばれることもありますが、Cyclosのレイアウト(ログインステータストップバー、左メニュー、機能ウィンドウ内)を定義します。テーマ機能は、一般的なスタイルシートファイルとスタイルシート画像を編集する必要なくレイアウトを切り替える素早い方法です。テーマ機能は「メニュー: コンテンツ管理 &gt; テーマ」から見つけられます。


<A NAME="select_theme"></A>
<h3>テーマ選択</h3>
<br><br>別の<a href="#themes"><u>テーマ</u></a>を選択するには、「テーマ」ドロップダウンボックスから１つを選び、それから「適用する」というラベルの付いた送信ボタンをクリックしなければなりません。テーマが表示されるようにするには、ブラウザを更新しなければならないでしょう。ブラウザキャッシュをクリアする必要がある場合もあります。<br> 以前にこの機能を使ったことがない場合は、利用可能なテーマが1つもなく、空のドロップダウンボックスになるかもしれないことに留意してください。そのような場合には、まず新しいテーマを<a href="#import_theme"><u>インポート</u></a>しなければならないでしょう。<br><br>「削除する」というラベルの付いた送信ボタンをクリックすると、そのテーマは削除され、カスタムレイアウト修正は消去されます。そのため、<a href="#css"><u>一般的スタイルシート</u></a>や<a href="#style_images"><u>スタイルシート画像</u></a>を独自にカスタマイズした場合には、まずそれをテーマとして<a href="#export_theme"><u>エクスポート</u></a>したいかもしれません。そうすると、それを後で再びインポートすることは容易です。テーマ機能は、スタイルシートのみに影響を与え、<a href="#statics"><u>静的ファイル</u></a>と<a href="#helps"><u>ヘルプファイル</u></a>には影響を与えません。
<hr class="help">


<A NAME="import_theme"></A>
<h3>新しいテーマをインポートする</h3>
<br><br>この機能によって<a href="#themes"><u>テーマ</u></a>をインポートできます。
Cyclosのテーマは.themeという拡張子を持ちます。 「ブラウズ」ボタンを使って、そのファイルの位置へとブラウズし、「送信」をクリックしてください。
<hr class="help">


<A NAME="export_theme"></A>
<h3>現在の設定をテーマとしてエクスポートする</h3>
<br><br>独自の<a href="#themes"><u>テーマ</u></a>を(<a href="#css"><u>スタイルシートファイル</u></a>および/または<a href="#style_images"><u>スタイルシート画像</u></a>の修正によって)作成した場合には、そのテーマを.themeファイルとしてエクスポートできます。<br> この機能により、現在のアクティブなテーマをエクスポートすることが可能になります。フィールドに記入して、下の「送信」ボタンをクリックしてください。<br>下のチェックボックスにチェックすることにより、テーマファイルの一部をエクスポートすることもできます。3つのオプションが利用可能です:
<ul>
	<li><b>システム</b> これらは中核的な<a href="#jsp"><u>.jsp</u></a>ファイルと<a href="#css"><u>.css</u></a>ファイルです。</li></li>
	<li><b>ログインページ</b> これは最初のログインページです。</li>
	<li><b>モバイル</b> これらは携帯電話によるアクセスのためのページです。</li>
</ul>
<br><br>
<i>独自のテーマを開発した場合には、それらをCyclos開発チームに送信することを検討してください。</i>そうすると、私たちはそれをCyclosの他のユーザーから利用可能にすることができます。
<hr>


<A NAME="infotexts"></A>
<h2>情報テキスト</h2> 情報テキストは、Webサービスから取得できるCyclos内に蓄えられたテキストです。典型的な用途はSMSモジュールです。 ユーザーはSMSによってキーワード、たとえば「宣伝」を送り、応答するテキストを受け取ることができます。情報テキストは、Cyclos内で登録し管理することができます。<br> <br>情報テキストには題名とメッセージ本文があります。 これはそれらがWebサイト内に含めるために使われる場合にはより一般的です。すべての情報テキストの特性についての詳細は、<a href="${pagePrefix}content_management#infotexts_edit"><u>情報テキストを編集する</u></a>ページで見つけられます。 <br> <br>Webサービスから情報テキストを取得する方法についての情報は、 <a href="http://project.cyclos.org/wiki/index.php?title=Web_services#Info_texts" target="_blank"><u>Wiki</u></a>で見つけられます。
<hr class="help"> 


<A NAME="infotext_search"></A>
<h2>情報テキストを検索する</h2> このページでは<a href="${pagePrefix}content_management#infotexts"><u>情報テキスト</u> </a>を検索でき、「新規作成ボタン」を選択することにより新しいものを作成できます。
<hr class="help">


<A NAME="infotexts_result"></A>
<h2>情報テキスト結果</h2> このページは検索の結果を表示します。
編集アイコン<img border="0" src="${images}/edit.gif" width="16" height="16">を選択して情報テキストを修正し、消去アイコン<img border="0" src="${images}/delete.gif" width="16" height="16">を選択して対応する情報テキストを消去することができます。
<hr class="help">


<A NAME="infotexts_edit"></A>
<h2>情報テキストを新規作成/編集する</h2>
このページでは、情報テキストのルールと内容を定義できます。
以下のオプションが利用可能です。
<ul>
	<li><b>キー: </b>これは対応するテキストフィールド(題名と本文)を取得するために使われるキーです。複数のキーが許されます。その場合、追加のキーは別名として機能します。
	<li><b>題名: </b>これは取得できるコンテンツのテキストです。
	短いテキスト(たとえばSMS)の場合、これは使用される唯一のテキストです。
	<li><b>本文テキスト: </b>題名と本文テキストの両方が使われる機能(たとえばWebサイト)については、本文テキストがここで定義されます。
	<li><b>有効期限</b> この設定は情報テキストがアクティブである(そして取得できる)期間を定めます。
	<li><b>有効</b> ここではすぐに情報テキストを有効化および無効化できます。無効の場合(チェックされていない場合)、情報テキストは取得できません。
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

</span>
