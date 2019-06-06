/*
 * Copyright (c) 2014, 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.controller.cluster.datastore.node.utils.stream;

import com.google.common.annotations.Beta;
import java.io.DataInput;
import java.io.IOException;
import java.util.Optional;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.data.impl.schema.ReusableImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;

/**
 * Interface for reading {@link NormalizedNode}s, {@link YangInstanceIdentifier}s, {@link PathArgument}s
 * and {@link SchemaPath}s.
 */
@Beta
public interface NormalizedNodeDataInput extends DataInput {
    /**
     * Interpret current stream position as a NormalizedNode, stream its events into a NormalizedNodeStreamWriter.
     *
     * @param writer Writer to emit events to
     * @throws IOException if an error occurs
     * @throws IllegalStateException if the dictionary has been detached
     * @throws NullPointerException if {@code writer} is null
     */
    void streamNormalizedNode(NormalizedNodeStreamWriter writer) throws IOException;

    /**
     * Read a normalized node from the reader.
     *
     * @return Next node from the stream, or null if end of stream has been reached.
     * @throws IOException if an error occurs
     * @throws IllegalStateException if the dictionary has been detached
     */
    default NormalizedNode<?, ?> readNormalizedNode() throws IOException {
        final NormalizedNodeResult result = new NormalizedNodeResult();
        try (NormalizedNodeStreamWriter writer = ImmutableNormalizedNodeStreamWriter.from(result)) {
            streamNormalizedNode(writer);
        }
        return result.getResult();
    }

    /**
     * Read a normalized node from the reader, using specified writer to construct the result.
     *
     * @param writer Reusable writer to
     * @return Next node from the stream, or null if end of stream has been reached.
     * @throws IOException if an error occurs
     * @throws IllegalStateException if the dictionary has been detached
     */
    default NormalizedNode<?, ?> readNormalizedNode(final ReusableImmutableNormalizedNodeStreamWriter writer)
            throws IOException {
        try {
            streamNormalizedNode(writer);
            return writer.getResult();
        } finally {
            writer.reset();
        }
    }

    YangInstanceIdentifier readYangInstanceIdentifier() throws IOException;

    @NonNull QName readQName() throws IOException;

    PathArgument readPathArgument() throws IOException;

    SchemaPath readSchemaPath() throws IOException;

    /**
     * Return the version of the underlying input stream.
     *
     * @return Stream version
     * @throws IOException if the version cannot be ascertained
     */
    NormalizedNodeStreamVersion getVersion() throws IOException;

    default Optional<NormalizedNode<?, ?>> readOptionalNormalizedNode() throws IOException {
        return readBoolean() ? Optional.of(readNormalizedNode()) : Optional.empty();
    }
}
