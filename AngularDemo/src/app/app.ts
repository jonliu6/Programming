import { Component, signal } from '@angular/core';
import { Content } from './content';

@Component({
  selector: 'app-root',
  imports: [Content],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  apptitle = 'Angular Demo';
  protected readonly title = signal(this.apptitle);
}
