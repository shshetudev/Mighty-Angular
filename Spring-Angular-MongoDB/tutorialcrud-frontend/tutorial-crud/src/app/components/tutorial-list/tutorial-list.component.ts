import {Component, OnInit} from '@angular/core';
import {TutorialService} from '../../services/tutorial.service';
import {Tutorial} from '../../model/tutorial';

@Component({
  selector: 'app-tutorial-list',
  templateUrl: './tutorial-list.component.html',
  styleUrls: ['./tutorial-list.component.css']
})
export class TutorialListComponent implements OnInit {

  // todo: change here
  tutorials: Tutorial[];
  currentTutorial: Tutorial = null;
  currentIndex = -1;
  title = '';

  constructor(private tutorialService: TutorialService) {
  }

  ngOnInit(): void {
    this.retrieveTutorials();
  }

  // todo: know the terms: subscribe(), Observable, Promise, Arrow function
  retrieveTutorials(): void {
    this.tutorialService.getAll()
      .subscribe(
        data => {
          this.tutorials = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveTutorials();
    this.currentTutorial = null;
    this.currentIndex = -1;
  }

  setActiveTutorial(tutorial, index): void {
    this.currentTutorial = tutorial;
    this.currentIndex = index;
  }

  removeAllTutorials(): void {
    this.tutorialService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        }, error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.tutorialService.findByTitle(this.title)
      .subscribe(
        data => {
          this.tutorials = data;
          console.log(data);
        }, error => {
          console.log(error);
        });
  }
}
