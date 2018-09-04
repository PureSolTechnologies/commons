import {Component, Input} from '@angular/core';

import {VerticalTabSetComponent} from './vertical-tabset.component';

@Component({
    selector: 'vertical-tab',
    template:
    `<div [hidden]="!active">
    <ng-content></ng-content>
</div>`
})
export class VerticalTabComponent {

    @Input() heading: string;
    active: boolean = false;

    constructor(tabs: VerticalTabSetComponent) {
        tabs.addTab(this);
    }

    getHeading(): string {
        return this.heading;
    }

    getClass(): string {
        if (this.active) {
            return "nav-link active";
        } else {
            return "nav-link";
        }
    }
}
