import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStakeholder } from 'app/shared/model/stakeholder.model';

@Component({
  selector: 'jhi-stakeholder-detail',
  templateUrl: './stakeholder-detail.component.html',
})
export class StakeholderDetailComponent implements OnInit {
  stakeholder: IStakeholder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stakeholder }) => (this.stakeholder = stakeholder));
  }

  previousState(): void {
    window.history.back();
  }
}
