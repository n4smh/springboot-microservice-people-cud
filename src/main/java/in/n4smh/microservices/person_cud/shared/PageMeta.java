package in.n4smh.microservices.person_cud.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageMeta {

	private long page;
	private long size;
	private long totalItems;
	private long totalPages;
	private boolean hasNext;
	private boolean hasPrevious;
}
