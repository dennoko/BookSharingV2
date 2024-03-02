# BookSharing App v2 の仕様書のような何か
##  アプリの機能
###   Home Screen
- 団体の書籍をタグごとに一覧表示
- 書籍のクリックによって書籍の詳細を見る
- 本の詳細から予約
- タグ検索

###   My page
- 自分の所有する書籍を一覧表示
- 書籍の追加
- 書籍の削除
- バーコード読み取りによる書籍の追加

###   Setting
- アプリの設定
- Home 画面のタグの並べ替え

###   その他機能
- ユーザー認証
- 画面遷移用のタブ

---
##  使用する API
###   国立国会図書館
- 国立国会図書館サーチ
- 書影API

国立国会図書館サーチ：書籍の情報の取得に利用

取得する書籍の情報
- タイトル
- 著者
- 出版日
- 価格
- ページ数
- ISBN

書影API：書影データの取得に利用 ※書影APIはISBN検索となる

---
##  使用するライブラリ
- Firebase
- Retrofit
- Coil
- Room
---

## データフロー
### 書籍情報の追加
1. ユーザーがバーコードを読み取るか、タイトルを入力する
2. 国立国会図書館サーチAPIを利用して書籍情報を取得
3. 取得した書籍情報をfirebaseに保存
4. 自分の所有する書籍の情報はRoomにも保存

```mermaid
flowchart TD
    A[バーコード読み取り] -->|バーコード情報| B[国立国会図書館サーチAPI]
    C[タイトル入力] -->|タイトル| B
    B -->|タイトル \n 著者 \n 出版日 \n 価格 \n ページ数 \n ISBN| D[Firebase]
    B -->|タイトル \n 著者 \n 出版日 \n 価格 \n ページ数 \n ISBN| E[Room]

    F[1.タイトルまたは\nバーコードから読み取ったISBN] --> |①\n書籍検索情報| H
    G[3.Retrofit] --> |⑦\n書籍情報| H[2.リポジトリクラス]
    H --> |②\n書籍検索情報| G
    H --> |⑧\n書籍情報+所有者| I[6.firebase]
    H --> |⑨\n書籍情報| J[7.Room]
    G --> |③\n書籍検索情報| K[4.国立国会図書館サーチAPI]
    K --> |④\n書籍情報※XML| G
    L[5.カスタムコンバータ] --> |⑥\n書籍情報※Kotlin オブジェクト|G
    G --> |⑤\n書籍情報※XML| L
```
プログラム内での動き（右図）
1. タイトルまたはバーコードから読み取ったISBNをリポジトリクラスに渡し、Retrofitを利用して国立国会図書館サーチAPIにアクセス
2. 国立国会図書館サーチAPIから書籍情報を取得（XML形式のデータ）
3. Retrofitのカスタムコンバータを利用して、XML形式のデータをKotlinオブジェクトに変換
4. 取得した書籍情報をFirebaseとRoomに保存

### 書籍情報の取得
1. Firebaseから書籍情報を取得
2. ISBNを利用して書影APIから書影データを取得
3. 取得した書籍情報と書影データを表示

```mermaid
flowchart TD
    A[1.Firebase] -->|書籍情報| B[2.書影API]
    B -->|書影データ| C[3.表示]

    D[UIの描画や更新] --> |①\n関数呼び出し|E[ViewModel]
    E --> |②\n関数呼び出し| F[リポジトリクラス]
    F --> |③\n書籍情報の取得| G[Firebase※My pageではRoom]
    G --> |④\n書籍情報| F
    F --> |⑤\nISBN検索| H[書影API]
    H --> |⑥\n書影URL| F
    F --> |⑦\n書籍情報+書影URL| E
    E --> |⑧\n書籍情報+書影URL| D
    D --> |⑨\n書影URL| I[Coil]
    I --> |⑩\n書影表示| D
```

プログラム内での動き（右図）
1. UIの描画や更新をトリガーに、ViewModelの関数を呼び出し
2. ViewModelからリポジトリクラスの関数を呼び出し、Firebaseから書籍情報を取得
3. 取得した書籍情報をUIに表示
4. 書籍情報からISBNを利用して書影APIから書影URLを取得
5. 取得した書影URLの画像をCoilで表示