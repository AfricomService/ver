import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VerprojectsSharedModule } from 'app/shared/shared.module';
import { CommentComponent } from './comment.component';
import { CommentDetailComponent } from './comment-detail.component';
import { CommentUpdateComponent } from './comment-update.component';
import { CommentDeleteDialogComponent } from './comment-delete-dialog.component';
import { commentRoute } from './comment.route';

@NgModule({
  imports: [VerprojectsSharedModule, RouterModule.forChild(commentRoute)],
  declarations: [CommentComponent, CommentDetailComponent, CommentUpdateComponent, CommentDeleteDialogComponent],
  entryComponents: [CommentDeleteDialogComponent],
})
export class VerprojectsCommentModule {}
