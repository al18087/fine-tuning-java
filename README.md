## このリポジトリを作成した目的  
OpenAIのファインチューニング実装方法について、Pythonはそれなりの記事が存在しているが、  
Javaで実装している記事がネットであまり見られないため、このリポジトリを通じて実装例を発信したい。  
  
## 使用方法 (※Eclipseの場合)  
1. 「サーバータグ > Tomcat9_Java17 > 追加及び除去」からaiプロジェクトを追加する。  
2. Tomcatを起動する。  
3. 「http://localhost:8080/ai/Main」を開く。  
  
## ファインチューニング  
### 前提
1. OpenAIアカウントを作成し、Api Keyを取得すること。  
2. 「src/main/WEB-INF/conf/openai.properties.sample」を、  
「src/main/WEB-INF/conf/openai.properties」にリネームすること。
3. 「src/main/WEB-INF/conf/openai.properties」のopenai_api_keyプロパティに、  
1で作成したAPI Keyを入力すること。
4. jsonlファイルを作成すること。  
※jsonlファイルのフォーマットは、学習モデルによって異なる。フォーマットは以下URLを参考  
https://platform.openai.com/docs/guides/fine-tuning/example-format  
  
### 実施方法  
1. jsonlファイルをアップロードする。  
2. 1を実施後、OpenAIアカウントに登録しているメールアドレスから、ファインチューニングの実施結果が送られる。  
  
## 推論  
### 前提  
1. OpenAIアカウントを作成し、Api Keyを取得すること。  
2. 「src/main/WEB-INF/conf/openai.properties.sample」を、  
「src/main/WEB-INF/conf/openai.properties」にリネームすること。  
3. 「src/main/WEB-INF/conf/openai.properties」の以下プロパティに値を入力すること。  
openai_api_key:1で作成したApi Key  
learned_model_name:ファインチューニングで作成したモデル名称 (ファインチューニングの実施方法の2で、メールに記載されている。)  
echo:true or false  
max_token_num:任意    
temperature:0～1  
suffix:任意  
  
## 最後に  
公開しているソースコードはあくまで例です。自由にカスタマイズして構いません。  
基本的に推論とファインチューニングのみ実装しています。  
DBを使用したい場合、O/RマッパーとしてHibernateを用意していますが、Hibernateを使用するかどうかは自由です。  
  
## 参考  
### ファインチューニングおよび推論を行うために使用したライブラリ      
openai-java  
https://github.com/TheoKanning/openai-java  
  
Libraries (OpenAI公式)  
https://platform.openai.com/docs/libraries/java


