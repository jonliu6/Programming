import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { App } from './app/app';
import { Content } from './app/content';

bootstrapApplication(App, appConfig)
  .catch((err) => console.error(err));
bootstrapApplication(Content, appConfig)
  .catch((err) => console.error(err));