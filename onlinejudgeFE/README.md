### 설정 방법

```bash
# NPM을 이용해 인스톨을 수행합니다.
sudo npm install

# 웹팩3 DLL 레퍼런스를 이용해 빌드 시간을 절약합니다.
# 기본적으로 이 명령어는 build/webpack.dll.conf.js 내부의 패키지를 업그레이드하지 않았다면 한 번만 수행하면 됩니다. (매 번 실행할 필요가 없음.)
NODE_ENV=development sudo npm run build:dll

# 개발 서버에서 백 엔드 서버에 프록시 테이블을 설정합니다.
export TARGET=http://localhost:81/

# 기본적으로 8081 포트에서 프론트 서버가 생성됩니다.
npm run dev
```bash


