import {Component, Input} from 'angular2/core';

import {VerticalTabComponent} from './vertical-tab.component';

@Component({
    selector: 'vertical-tabset',
    directives: [],
    template:
    `<div class="row">
       <div class="col-md-4">
         <ul class="nav nav-pills nav-stacked">
           <li class="nav-item" *ngFor="#tab of tabs">
             <a class="{{tab.getClass()}}" (click)="selectTab(tab)">{{tab.getHeading()}}</a>
           </li>
         </ul>
       </div>
       <div class="col-md-8">
         <ng-content></ng-content>
       </div>
    </div>
    `
})
export class VerticalTabSetComponent {

    tabs: VerticalTabComponent[] = [];

    addTab(tab: VerticalTabComponent) {
        if (this.tabs.length === 0) {
            tab.active = true;
        }
        this.tabs.push(tab);
    }

    selectTab(tab: VerticalTabComponent) {
        this.tabs.forEach((tab) => {
            tab.active = false;
        });
        tab.active = true;
    }
}
