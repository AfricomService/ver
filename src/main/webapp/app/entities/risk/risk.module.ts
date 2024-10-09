import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VerprojectsSharedModule } from 'app/shared/shared.module';
import { RiskComponent } from './risk.component';
import { RiskDetailComponent } from './risk-detail.component';
import { RiskUpdateComponent } from './risk-update.component';
import { RiskDeleteDialogComponent } from './risk-delete-dialog.component';
import { riskRoute } from './risk.route';

@NgModule({
  imports: [VerprojectsSharedModule, RouterModule.forChild(riskRoute)],
  declarations: [RiskComponent, RiskDetailComponent, RiskUpdateComponent, RiskDeleteDialogComponent],
  entryComponents: [RiskDeleteDialogComponent],
})
export class VerprojectsRiskModule {}
