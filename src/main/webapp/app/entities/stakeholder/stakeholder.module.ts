import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VerprojectsSharedModule } from 'app/shared/shared.module';
import { StakeholderComponent } from './stakeholder.component';
import { StakeholderDetailComponent } from './stakeholder-detail.component';
import { StakeholderUpdateComponent } from './stakeholder-update.component';
import { StakeholderDeleteDialogComponent } from './stakeholder-delete-dialog.component';
import { stakeholderRoute } from './stakeholder.route';

@NgModule({
  imports: [VerprojectsSharedModule, RouterModule.forChild(stakeholderRoute)],
  declarations: [StakeholderComponent, StakeholderDetailComponent, StakeholderUpdateComponent, StakeholderDeleteDialogComponent],
  entryComponents: [StakeholderDeleteDialogComponent],
})
export class VerprojectsStakeholderModule {}
