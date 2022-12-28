## Material3 Calculator (Google Pixel app copy)

The app was created to use JetpackCompose, setup bitrise workflows (develop, staging, main) with github pull requests.

#### What development process is setup?

- Github pull request;
- Bitrise
    - PR from any branch to `develop` (runs lint checks and unit tests)
    - PR from `develop` to `staging` (runs lint checks, unit/instrumented tests, use `Firebase App Distribution` to provide a generated app for testers)
    - PR from `staging` to `main` (deploy an app to PlayMarket)

### Light/Dark mode
<img src="/screenshots/screen0.png" width="320"/> <img src="/screenshots/screen1.png" width="320"/>