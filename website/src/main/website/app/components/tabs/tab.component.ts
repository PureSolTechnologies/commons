import {Component, Input} from '@angular/core';

import {TabSetComponent} from './tabset.component';

@Component({
    selector: 'tab',
    template:
    `<div [hidden]="!active">
    <ng-content></ng-content>
</div>`
})
export class TabComponent {

    @Input() heading: string;
    active: boolean = false;

    constructor(tabs: TabSetComponent) {
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
