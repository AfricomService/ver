import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStakeholder } from 'app/shared/model/stakeholder.model';
import { StakeholderService } from './stakeholder.service';

@Component({
  templateUrl: './stakeholder-delete-dialog.component.html',
})
export class StakeholderDeleteDialogComponent {
  stakeholder?: IStakeholder;

  constructor(
    protected stakeholderService: StakeholderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stakeholderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('stakeholderListModification');
      this.activeModal.close();
    });
  }
}
